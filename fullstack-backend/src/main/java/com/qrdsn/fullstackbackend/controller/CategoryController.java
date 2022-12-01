package com.qrdsn.fullstackbackend.controller;

import com.qrdsn.fullstackbackend.model.dto.CategoryDTO;
import com.qrdsn.fullstackbackend.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final InventoryService inventoryService;

    @Autowired
    public CategoryController(InventoryService inventoryService){
        this.inventoryService = inventoryService;
    }

    //  Insert category
    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO newCategory){
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.createCategory(newCategory));
    }

    //  Get all categories
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll(){
        List<CategoryDTO> categoryViewModelList = inventoryService.findAllCategories();
        if (categoryViewModelList != null ) {
            return ResponseEntity.ok(categoryViewModelList);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }


    //  Get category information with id
    @GetMapping("{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(inventoryService.findCategory(id));
    }

    //  Update category information
    @PutMapping
    public ResponseEntity<CategoryDTO> update(@RequestBody CategoryDTO newCategory){
        return ResponseEntity.ok(inventoryService.updateCategory(newCategory));
    }

    //  Delete the category with id
    @DeleteMapping("{id}")
    public ResponseEntity<CategoryDTO> delete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.deleteCategory(id));
    }
}