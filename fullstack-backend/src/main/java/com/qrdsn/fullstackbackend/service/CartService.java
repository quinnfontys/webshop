package com.qrdsn.fullstackbackend.service;

import com.qrdsn.fullstackbackend.exception.NotFoundException;
import com.qrdsn.fullstackbackend.model.Cart;
import com.qrdsn.fullstackbackend.model.CartProduct;
import com.qrdsn.fullstackbackend.model.Product;
import com.qrdsn.fullstackbackend.model.dto.*;
import com.qrdsn.fullstackbackend.repository.CartProductRepository;
import com.qrdsn.fullstackbackend.repository.CartRepository;
import com.qrdsn.fullstackbackend.repository.ProductRespository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRespository productRepository;
    private final CartProductRepository cartProductRepository;

    @Autowired
    public CartService(CartRepository cartRepository, ProductRespository productRepository, CartProductRepository cartProductRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartProductRepository = cartProductRepository;
    }

    ModelMapper modelMapper = new ModelMapper();

    public CartProductDTO addProductToCart(CartProductDTO cartProductDTO){
        Cart cart;
        if(!cartRepository.existsById(cartProductDTO.getCart().getId())) {
            cart = new Cart();
            //missing user id
            cartRepository.save(cart);
            //TODO: preferably every user already has a cart
        } else {
            cart = cartRepository.findById(cartProductDTO.getCart().getId())
                    .orElseThrow(()->new NotFoundException("Could not find cart " + cartProductDTO.getCart().getId()));
        }

        Product product = productRepository.findById(cartProductDTO.getProduct().getId())
                .orElseThrow(()->new NotFoundException("Could not find product " + cartProductDTO.getProduct().getId()));

        CartProduct cartProduct = cartProductRepository.findByCartIdAndProductId(cartProductDTO.getCart().getId(), cartProductDTO.getProduct().getId());
        if (cartProduct != null) {
            cartProduct.setQuantity(cartProduct.getQuantity() + 1);
        } else {
            cartProduct = new CartProduct();
            cartProduct.setCart(cart);
            cartProduct.setProduct(product);
            cartProduct.setQuantity(1);
        }
        cartProductRepository.save(cartProduct);
        return modelMapper.map(cartProduct, CartProductDTO.class);
    }

    public CartProductDTO removeProductFromCart(CartProductDTO cartProductDTO) {
        cartRepository.findById(cartProductDTO.getCart().getId())
                .orElseThrow(()->new NotFoundException("Could not find cart " + cartProductDTO.getCart().getId()));

        productRepository.findById(cartProductDTO.getProduct().getId())
                .orElseThrow(()->new NotFoundException("Could not find product " + cartProductDTO.getProduct().getId()));

        CartProduct cartProduct = cartProductRepository.findByCartIdAndProductId(cartProductDTO.getCart().getId(), cartProductDTO.getProduct().getId());
        if (cartProduct == null) {
            throw new NotFoundException("Could not find CartProduct");
        } else if (cartProduct.getQuantity() <= 1) {
            cartProductRepository.delete(cartProduct);
        } else {
            cartProduct.setQuantity(cartProduct.getQuantity() - 1);
            cartProductRepository.save(cartProduct);
        }
        return modelMapper.map(cartProduct, CartProductDTO.class);
    }

    public CartProductDTO editCartProductQuantity(CartProductDTO cartProductDTO) {
        cartRepository.findById(cartProductDTO.getCart().getId())
                .orElseThrow(()->new NotFoundException("Could not find cart " + cartProductDTO.getCart().getId()));

        productRepository.findById(cartProductDTO.getProduct().getId())
                .orElseThrow(()->new NotFoundException("Could not find product " + cartProductDTO.getProduct().getId()));

        CartProduct cartProduct = cartProductRepository.findByCartIdAndProductId(cartProductDTO.getCart().getId(), cartProductDTO.getProduct().getId());
        if (cartProduct == null) {
            throw new NotFoundException("Could not find CartProduct");
        } else if (cartProduct.getQuantity() <= 1) {
            cartProductRepository.delete(cartProduct);
        } else {
            cartProduct.setQuantity(cartProductDTO.getQuantity());
            cartProductRepository.save(cartProduct);
        }

        return modelMapper.map(cartProduct, CartProductDTO.class);
    }


    public CartDTO findCartFromUser(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            throw new NotFoundException("Could not find cart with user " + userId);
        }
        return modelMapper.map(cart, CartDTO.class);
    }
}