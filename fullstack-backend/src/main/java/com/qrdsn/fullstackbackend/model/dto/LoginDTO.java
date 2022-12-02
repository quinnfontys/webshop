package com.qrdsn.fullstackbackend.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO extends DTO {
    public String email;
    public String password;

    public LoginDTO(String email, String password){
        this.email = email;
        this.password = password;
    }
}
