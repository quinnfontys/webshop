package com.qrdsn.fullstackbackend.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.qrdsn.fullstackbackend.model.dto.EditUserDTO;
import com.qrdsn.fullstackbackend.model.dto.UserDTO;
import com.qrdsn.fullstackbackend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    ModelMapper modelMapper = new ModelMapper();

    List<UserDTO> userDTOList = new ArrayList<UserDTO>();
    @BeforeEach
    public void setup(){
        UserDTO userDTO = new UserDTO();

        userDTO.setId(1L);
        userDTO.setEmail("Test@Example.com");
        userDTOList.add(userDTO);

        userDTO = new UserDTO();
        userDTO.setId(2L);
        userDTO.setEmail("quinnfontys@gmail.com");
        userDTOList.add(userDTO);
    }


    @Test
    public void create(){
        UserDTO user = new UserDTO();
        user.setId(1L);
        user.setEmail("quinnfontys@gmail.com");

        when(userService.create(any(UserDTO.class))).thenReturn(user);

        ResponseEntity<UserDTO> result = userController.create(user);

        assertThat(result.getStatusCodeValue()).isEqualTo(201);
        assertThat(result.getBody()).isEqualTo(user);
    }

    @Test
    void findAll() {
        when(userService.findAll()).thenReturn(userDTOList);

        ResponseEntity<List<UserDTO>> result = userController.findAll();

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().size()).isEqualTo(2);
        assertThat(result.getBody().get(0).getEmail()).isEqualTo(userDTOList.get(0).getEmail());
        assertThat(result.getBody().get(1).getEmail()).isEqualTo(userDTOList.get(1).getEmail());
    }

    @Test
    void getById() {
        when(userService.findById(any(Long.class))).thenReturn(userDTOList.get(0));

        ResponseEntity<UserDTO> result = userController.getById(1L);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getEmail()).isEqualTo(userDTOList.get(0).getEmail());
        assertThat(result.getBody()).isEqualTo(userDTOList.get(0));
    }

    @Test
    void update() {
        EditUserDTO user = new EditUserDTO();
        user.setId(1L);
        user.setEmail("quinnfontys@gmail.com");

        when(userService.update(any(EditUserDTO.class))).thenReturn(modelMapper.map(user, UserDTO.class));

        ResponseEntity<UserDTO> result = userController.update(user);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody()).isEqualTo(user);
    }

    @Test
    void delete() {
        when(userService.delete(any(Long.class))).thenReturn(userDTOList.get(0));

        ResponseEntity<UserDTO> result = userController.delete(1L);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody()).isEqualTo(userDTOList.get(0));
    }
}
