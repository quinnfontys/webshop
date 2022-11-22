package com.qrdsn.fullstackbackend.controller;

import com.qrdsn.fullstackbackend.model.dto.ProductDTO;
import com.qrdsn.fullstackbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    //  Insert product
    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO newProduct){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create((newProduct)));
    }

    //  Get all products
    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll(){
        List<ProductDTO> productViewModelList = productService.findAll();
        if (productViewModelList != null ) {
            return ResponseEntity.ok(productViewModelList);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }


    //  Get product information with id
    @GetMapping("{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(productService.findById(id));
    }

    //  Update product information
    @PutMapping
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO newProduct){
        return ResponseEntity.ok(productService.update(newProduct));
    }

    //  Delete the product with id
    @DeleteMapping("{id}")
    public ResponseEntity<ProductDTO> delete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(productService.delete(id));
    }
}
