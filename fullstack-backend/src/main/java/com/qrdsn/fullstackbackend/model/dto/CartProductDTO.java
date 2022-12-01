package com.qrdsn.fullstackbackend.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartProductDTO {
    private ProductDTO product;
    @JsonBackReference
    private CartDTO cart;
    private int quantity;
}