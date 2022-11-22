package com.qrdsn.fullstackbackend.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO extends DTO {
    private Long id;
    private String name;
    private String email;
    private String password;
}
