package com.qrdsn.fullstackbackend.controller;

import com.qrdsn.fullstackbackend.model.dto.CategoryDTO;
import com.qrdsn.fullstackbackend.service.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {
    @InjectMocks
    CategoryController categoryController;
    @Mock
    InventoryService inventoryService;

    List<CategoryDTO> categoryDTOList = new ArrayList<CategoryDTO>();
    @BeforeEach
    public void setup(){
        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId(1L);
        categoryDTO.setName("Apples");
        categoryDTOList.add(categoryDTO);

        categoryDTO = new CategoryDTO();
        categoryDTO.setId(2L);
        categoryDTO.setName("Bananas");
        categoryDTOList.add(categoryDTO);
    }


    @Test
    public void create(){
        CategoryDTO category = new CategoryDTO();
        category.setId(1L);
        category.setName("Oranges");

        when(inventoryService.createCategory(any(CategoryDTO.class))).thenReturn(category);

        ResponseEntity<CategoryDTO> result = categoryController.create(category);

        assertThat(result.getStatusCodeValue()).isEqualTo(201);
        assertThat(result.getBody()).isEqualTo(category);
    }

    @Test
    void findAll() {
        when(inventoryService.findAllCategories()).thenReturn(categoryDTOList);

        ResponseEntity<List<CategoryDTO>> result = categoryController.findAll();

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().size()).isEqualTo(2);
        assertThat(result.getBody().get(0).getName()).isEqualTo(categoryDTOList.get(0).getName());
        assertThat(result.getBody().get(1).getName()).isEqualTo(categoryDTOList.get(1).getName());
    }

    @Test
    void getById() {
        when(inventoryService.findCategory(any(Long.class))).thenReturn(categoryDTOList.get(0));

        ResponseEntity<CategoryDTO> result = categoryController.getById(1L);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getName()).isEqualTo(categoryDTOList.get(0).getName());
        assertThat(result.getBody()).isEqualTo(categoryDTOList.get(0));
    }

    @Test
    void update() {
        CategoryDTO category = new CategoryDTO();
        category.setId(1L);
        category.setName("Oranges");

        when(inventoryService.updateCategory(any(CategoryDTO.class))).thenReturn(category);

        ResponseEntity<CategoryDTO> result = categoryController.update(category);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody()).isEqualTo(category);
    }

    @Test
    void delete() {
        when(inventoryService.deleteCategory(any(Long.class))).thenReturn(categoryDTOList.get(0));

        ResponseEntity<CategoryDTO> result = categoryController.delete(1L);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody()).isEqualTo(categoryDTOList.get(0));
    }
}