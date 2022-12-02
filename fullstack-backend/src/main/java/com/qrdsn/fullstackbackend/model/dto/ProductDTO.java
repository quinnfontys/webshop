package com.qrdsn.fullstackbackend.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO extends DTO {
    private Long id;
    private String name;
    private CategoryDTO category;
    private String description;
    private String image_file;
    private Float price;
}
