package com.qrdsn.fullstackbackend.controller;

import com.qrdsn.fullstackbackend.model.JWSData;
import com.qrdsn.fullstackbackend.model.dto.*;
import com.qrdsn.fullstackbackend.service.CartService;
import com.qrdsn.fullstackbackend.service.JWTService;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;
    private final JWTService jwtService;

    @Autowired
    public CartController(CartService cartService, JWTService jwtService){
        this.cartService = cartService;
        this.jwtService = jwtService;
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

    @GetMapping
    public ResponseEntity<CartDTO> findCartFromUser(@RequestParam String jwsString) {
        try {
            JWSData data = jwtService.verifyJWS(new JWSDTO(jwsString));
            return ResponseEntity.status(HttpStatus.OK).body(cartService.findCartFromUser(Long.parseLong(data.getId())));
        } catch (JwtException jwtException) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}