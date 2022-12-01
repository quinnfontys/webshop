package com.qrdsn.fullstackbackend.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditUserDTO extends DTO {
    private Long id;
    private String email;
    private String password;
    private Byte role;
}
