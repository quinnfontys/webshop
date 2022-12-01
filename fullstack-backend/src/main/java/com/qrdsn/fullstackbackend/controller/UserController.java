package com.qrdsn.fullstackbackend.controller;

import com.qrdsn.fullstackbackend.model.dto.EditUserDTO;
import com.qrdsn.fullstackbackend.model.dto.LoginDTO;
import com.qrdsn.fullstackbackend.model.dto.RegisterDTO;
import com.qrdsn.fullstackbackend.model.dto.UserDTO;
import com.qrdsn.fullstackbackend.service.AuthenticationService;
import com.qrdsn.fullstackbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Autowired
    public UserController(UserService userService, AuthenticationService authenticationService){
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    //  Insert user
    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO newUser){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(newUser));
    }

    //  Get all users
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<UserDTO> userDTOList = userService.findAll();
        if (userDTOList != null ) {
            return ResponseEntity.ok(userDTOList);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }


    //  Get user information with id
    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    //  Update user information
    @PutMapping
    public ResponseEntity<UserDTO> update(@RequestBody EditUserDTO newUser){
        return ResponseEntity.ok(userService.update(newUser));
    }

    //  Delete the user with id
    @DeleteMapping("{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.delete(id));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterDTO> register(@RequestBody RegisterDTO registerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.register(registerDTO));
    }

    @GetMapping("/verify")
    public ResponseEntity<UserDTO> changePassword(@RequestParam Long id, @RequestParam String token) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.verify(token, id));
    }

    @PutMapping("/changePassword")
    public ResponseEntity<UserDTO> changePassword(@RequestBody EditUserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.changePassword(userDTO));
    }

    @GetMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.login(loginDTO));
    }
}
