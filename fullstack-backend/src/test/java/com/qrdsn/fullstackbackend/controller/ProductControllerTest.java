package com.qrdsn.fullstackbackend.controller;

import com.qrdsn.fullstackbackend.model.dto.ProductDTO;
import com.qrdsn.fullstackbackend.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @InjectMocks
    ProductController productController;

    @MockBean
    ProductService productService;

    List<ProductDTO> productDTOList = new ArrayList<ProductDTO>();
    @BeforeEach
    public void setup(){
        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(1L);
        productDTO.setName("Gala");
        productDTOList.add(productDTO);

        productDTO = new ProductDTO();
        productDTO.setId(2L);
        productDTO.setName("Golden Delicious");
        productDTOList.add(productDTO);
    }


    @Test
    public void create(){
        ProductDTO product = new ProductDTO();
        product.setId(1L);
        product.setName("Cavendish");

        when(productService.create(any(ProductDTO.class))).thenReturn(product);

        ResponseEntity<ProductDTO> result = productController.create(product);

        assertThat(result.getStatusCodeValue()).isEqualTo(201);
        assertThat(result.getBody()).isEqualTo(product);
    }

    @Test
    void findAll() {
        when(productService.findAll()).thenReturn(productDTOList);

        ResponseEntity<List<ProductDTO>> result = productController.findAll();

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().size()).isEqualTo(2);
        assertThat(result.getBody().get(0).getName()).isEqualTo(productDTOList.get(0).getName());
        assertThat(result.getBody().get(1).getName()).isEqualTo(productDTOList.get(1).getName());
    }

    @Test
    void getById() {
        when(productService.findById(any(Long.class))).thenReturn(productDTOList.get(0));

        ResponseEntity<ProductDTO> result = productController.getById(1L);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getName()).isEqualTo(productDTOList.get(0).getName());
        assertThat(result.getBody()).isEqualTo(productDTOList.get(0));
    }

    @Test
    void update() {
        ProductDTO product = new ProductDTO();
        product.setId(1L);
        product.setName("Cavendish");

        when(productService.update(any(ProductDTO.class))).thenReturn(product);

        ResponseEntity<ProductDTO> result = productController.update(product);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody()).isEqualTo(product);
    }

    @Test
    void delete() {
        when(productService.delete(any(Long.class))).thenReturn(productDTOList.get(0));

        ResponseEntity<ProductDTO> result = productController.delete(1L);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody()).isEqualTo(productDTOList.get(0));
    }
}