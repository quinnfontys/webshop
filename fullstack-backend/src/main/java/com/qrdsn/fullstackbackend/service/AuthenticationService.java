package com.qrdsn.fullstackbackend.service;

import com.qrdsn.fullstackbackend.exception.AlreadyExistsException;
import com.qrdsn.fullstackbackend.exception.InvalidInputException;
import com.qrdsn.fullstackbackend.exception.NotFoundException;
import com.qrdsn.fullstackbackend.model.User;
import com.qrdsn.fullstackbackend.model.dto.EditUserDTO;
import com.qrdsn.fullstackbackend.model.dto.LoginDTO;
import com.qrdsn.fullstackbackend.model.dto.RegisterDTO;
import com.qrdsn.fullstackbackend.model.dto.UserDTO;
import com.qrdsn.fullstackbackend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final MailService mailService;

    @Autowired
    public AuthenticationService(UserRepository userRepository, MailService mailService) {
        this.userRepository = userRepository;
        this.mailService = mailService;
    }

    ModelMapper modelMapper = new ModelMapper();
    PasswordEncoder encoder = new BCryptPasswordEncoder();

    public RegisterDTO register(RegisterDTO registerDTO) {
        if (!registerDTO.getEmail().contains("@")) {
            //invalid email
            throw new InvalidInputException("Email must contain @ symbol");
        }
        if (userRepository.existsByEmail(registerDTO.getEmail().toLowerCase())){
            // email already exists
            throw new AlreadyExistsException("Email already exists");
        }

        String token = randomString(32);

        User user = new User();
        user.setPassword(encoder.encode(token));
        user.setEmail(registerDTO.getEmail().toLowerCase());
        user.setRole((byte)1);
        User newUser = userRepository.save(user);

        String link = createLink(token, newUser.getId());
        System.out.println(link);
        mailService.sendVerifyMail(user.getEmail(), link);

        return modelMapper.map(user, RegisterDTO.class);
    }

    public UserDTO verify(String token, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Could not find user " + id));

        return login(token, user);
    }

    public UserDTO changePassword(EditUserDTO userDTO) {
        if (userDTO.getPassword().length() < 8) {
            throw new InvalidInputException("Password must be 8 or more characters");
        }

        User user = userRepository.findById(userDTO.getId())
                .orElseThrow(()->new NotFoundException("Could not find user " + userDTO.getId()));

        user.setPassword(encoder.encode(userDTO.getPassword()));
        return modelMapper.map(userRepository.save(modelMapper.map(user, User.class)), UserDTO.class);
    }

    public UserDTO login(LoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.getEmail().toLowerCase());
        if (user == null) {
            throw new NotFoundException(String.format("User with email: %s not found", loginDTO.getEmail()));
        }
        return login(loginDTO.getPassword(), user);
    }

    private UserDTO login(String password, User user) {
        if(encoder.matches(password, user.getPassword())) {
            //successfully logged in
            return modelMapper.map(userRepository.save(modelMapper.map(user, User.class)), UserDTO.class);
        }
        throw new NotFoundException("Login failed");
    }

    private String createLink(String token, Long userId) {
        String domain = "http://localhost:8090/api/user/verify";
        return (domain + "?id=" + userId + "&token=" + token);
    }

    private String randomString(int length) {
        char[] ALPHANUMERIC ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

        StringBuilder string = new StringBuilder();

        for(int i = 0; i < length; i++) {
            Random random = new Random();
            int index = random.nextInt(ALPHANUMERIC.length);

            string.append(ALPHANUMERIC[index]);
        }
        return string.toString();
    }

}
