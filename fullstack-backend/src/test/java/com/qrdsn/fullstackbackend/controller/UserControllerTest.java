package com.qrdsn.fullstackbackend.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.qrdsn.fullstackbackend.model.User;
import com.qrdsn.fullstackbackend.model.dto.UserDTO;
import com.qrdsn.fullstackbackend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations = "/test-context.xml")
public class UserControllerTest {
    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    List<UserDTO> userDTOList = new ArrayList<UserDTO>();
    @BeforeEach
    public void setup(){
        UserDTO userDTO = new UserDTO();

        userDTO.setId(1L);
        userDTO.setEmail("Test@Example.com");
        userDTO.setName("test");
        userDTO.setPassword("abcdefg");
        userDTOList.add(userDTO);

        userDTO = new UserDTO();
        userDTO.setId(2L);
        userDTO.setEmail("quinnfontys@gmail.com");
        userDTO.setName("quinn");
        userDTO.setPassword("zyxwvut");
        userDTOList.add(userDTO);
    }


    @Test
    public void create(){
        UserDTO user = new UserDTO();
        user.setId(1L);
        user.setEmail("quinnfontys@gmail.com");
        user.setName("quinn");
        user.setPassword("abcdefg");

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
        assertThat(result.getBody().get(0).getName()).isEqualTo(userDTOList.get(0).getName());
        assertThat(result.getBody().get(1).getName()).isEqualTo(userDTOList.get(1).getName());
    }

    @Test
    void getById() {
        when(userService.findById(any(Long.class))).thenReturn(userDTOList.get(0));

        ResponseEntity<UserDTO> result = userController.getById(1L);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getName()).isEqualTo(userDTOList.get(0).getName());
        assertThat(result.getBody()).isEqualTo(userDTOList.get(0));
    }

    @Test
    void update() {
        UserDTO user = new UserDTO();
        user.setId(1L);
        user.setEmail("quinnfontys@gmail.com");
        user.setName("quinn");
        user.setPassword("abcdefg");

        when(userService.update(any(UserDTO.class))).thenReturn(user);

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
