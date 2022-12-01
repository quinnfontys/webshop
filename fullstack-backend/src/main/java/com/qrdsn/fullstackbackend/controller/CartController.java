package com.qrdsn.fullstackbackend.controller;

import com.qrdsn.fullstackbackend.model.dto.*;
import com.qrdsn.fullstackbackend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<CartProductDTO> addProductToCart(@RequestBody CartProductDTO cartProductDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.addProductToCart(cartProductDTO));
    }

    @PutMapping
    public ResponseEntity<CartProductDTO> editCartProductQuantity(@RequestBody CartProductDTO cartProductDTO){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.editCartProductQuantity(cartProductDTO));
    }

    @DeleteMapping
    public ResponseEntity<CartProductDTO> removeProductFromCart(@RequestBody CartProductDTO cartProductDTO){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.removeProductFromCart(cartProductDTO));
    }

    @GetMapping("{userId}")
    public ResponseEntity<CartDTO> findCartFromUser(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.findCartFromUser(userId));
    }
}
