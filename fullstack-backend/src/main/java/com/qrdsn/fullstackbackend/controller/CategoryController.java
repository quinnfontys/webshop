package com.qrdsn.fullstackbackend.controller;

import com.qrdsn.fullstackbackend.model.dto.CategoryDTO;
import com.qrdsn.fullstackbackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    //  Insert category
    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO newCategory){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(newCategory));
    }

    //  Get all categories
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll(){
        List<CategoryDTO> categoryViewModelList = categoryService.findAll();
        if (categoryViewModelList != null ) {
            return ResponseEntity.ok(categoryViewModelList);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }


    //  Get category information with id
    @GetMapping("{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.findById(id));
    }

    //  Update category information
    @PutMapping
    public ResponseEntity<CategoryDTO> update(@RequestBody CategoryDTO newCategory){
        return ResponseEntity.ok(categoryService.update(newCategory));
    }

    //  Delete the category with id
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.delete(id));
    }
}