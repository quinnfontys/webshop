package com.qrdsn.fullstackbackend.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO extends DTO {
    private Long id;
    private String name;
    private Long categoryId;
}
