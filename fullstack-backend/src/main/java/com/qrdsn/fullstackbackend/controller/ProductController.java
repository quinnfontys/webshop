package com.qrdsn.fullstackbackend.controller;

import com.qrdsn.fullstackbackend.model.Product;
import com.qrdsn.fullstackbackend.model.dto.ProductDTO;
import com.qrdsn.fullstackbackend.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {
    private final InventoryService inventoryService;

    @Autowired
    public ProductController(InventoryService inventoryService){
        this.inventoryService = inventoryService;
    }

    //  Insert product
    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO newProduct){
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.createProduct(newProduct));
    }

    //  Get all products
    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> findAll(){
        List<ProductDTO> productViewModelList = inventoryService.findAllProducts();
        if (productViewModelList != null ) {
            return ResponseEntity.ok(productViewModelList);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    // Get all products by category
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductDTO>> findAllProductsByCategory(@PathVariable Long categoryId){
        List<ProductDTO> productViewModelList = inventoryService.findAllProductsByCategoryId(categoryId);
        if (productViewModelList != null ) {
            return ResponseEntity.ok(productViewModelList);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

//    @GetMapping("/all")
//    public ResponseEntity<List<ProductDTO>> findAll(@Nullable @RequestParam Long categoryId){
//        List<ProductDTO> productViewModelList;
//        if (categoryId == null) {
//            productViewModelList = inventoryService.findAllProducts();
//        } else {
//            productViewModelList = inventoryService.findAllProductsByCategoryId(categoryId);
//        }
//        if (productViewModelList != null ) {
//            return ResponseEntity.ok(productViewModelList);
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//    }


    //  Get product information with id
    @GetMapping
    public ResponseEntity<ProductDTO> getById(@RequestParam Long id){
        return ResponseEntity.ok(inventoryService.findProduct(id));
    }

    //  Update product information
    @PutMapping
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO newProduct){
        return ResponseEntity.ok(inventoryService.updateProduct(newProduct));
    }

    //  Delete the product with id
    @DeleteMapping
    public ResponseEntity<ProductDTO> delete(@RequestParam Long id){
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.deleteProduct(id));
    }
}
