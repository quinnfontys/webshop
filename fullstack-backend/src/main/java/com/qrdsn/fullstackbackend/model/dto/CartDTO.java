package com.qrdsn.fullstackbackend.model.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CartDTO {
    private Long id;
    UserDTO user;
    @JsonManagedReference
    private Set<CartProductDTO> cartProducts;
}
