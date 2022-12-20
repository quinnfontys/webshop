package com.qrdsn.fullstackbackend.service;

import com.qrdsn.fullstackbackend.exception.NotFoundException;
import com.qrdsn.fullstackbackend.model.User;
import com.qrdsn.fullstackbackend.model.dto.EditUserDTO;
import com.qrdsn.fullstackbackend.model.dto.UserDTO;
import com.qrdsn.fullstackbackend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    ModelMapper modelMapper = new ModelMapper();

    public UserDTO create(UserDTO user){
        return modelMapper.map(userRepository.save(modelMapper.map(user, User.class)), UserDTO.class);
    }

    public List<UserDTO> findAll(){
        return Arrays.asList(modelMapper.map(userRepository.findAll(), UserDTO[].class));

    }

    public UserDTO findById(Long id){
        return modelMapper.map(userRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Could not find user " + id)), UserDTO.class);
    }

    public UserDTO update(EditUserDTO newUser){
        EditUserDTO editUserDTO = modelMapper.map(findById(newUser.getId()), EditUserDTO.class);
        if (newUser.getPassword() != null) {
            editUserDTO.setPassword(encoder.encode(newUser.getPassword()));
        }
        if (newUser.getEmail() != null) {
            //send email as verification or something?
        }
        editUserDTO.setRole((byte)1);

        return modelMapper.map(userRepository.save(modelMapper.map(editUserDTO, User.class)), UserDTO.class);
    }

    public UserDTO delete(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Could not find user " + id));
        userRepository.deleteById(id);
        return modelMapper.map(user, UserDTO.class);
    }
}
