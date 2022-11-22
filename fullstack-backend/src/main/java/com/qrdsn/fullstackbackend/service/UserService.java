package com.qrdsn.fullstackbackend.service;

import com.qrdsn.fullstackbackend.exception.UserNotFoundException;
import com.qrdsn.fullstackbackend.model.User;
import com.qrdsn.fullstackbackend.model.dto.UserDTO;
import com.qrdsn.fullstackbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserDTO userToUserDTO(User user){
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());

        return userDTO;
    }

    private User userDTOToUser(UserDTO userDTO){
        User user = new User();

        user.setId(userDTO.getId());
        user.setName(userDTO.getName());

        return user;
    }

    private List<UserDTO> userToUserDTO(List<User> userList){
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user: userList) {
            userDTOList.add(userToUserDTO(user));
        }
        return userDTOList;
    }

    public UserDTO create(UserDTO user){
        return userToUserDTO(userRepository.save(userDTOToUser(user)));
    }

    public List<UserDTO> findAll(){
        return userToUserDTO(userRepository.findAll());
    }

    public UserDTO findById(Long id){
        return userToUserDTO(userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id)));
    }

    public UserDTO update(UserDTO newUser){
        if (userRepository.existsById(newUser.getId())) {
            return userToUserDTO(userRepository.save(userDTOToUser(newUser)));
        }
        throw new UserNotFoundException(newUser.getId());
    }

    public UserDTO delete(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
        userRepository.deleteById(id);
        return userToUserDTO(user);
    }
}
