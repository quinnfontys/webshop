package com.qrdsn.fullstackbackend.service;

import com.qrdsn.fullstackbackend.exception.ProductNotFoundException;
import com.qrdsn.fullstackbackend.model.Product;
import com.qrdsn.fullstackbackend.model.dto.ProductDTO;
import com.qrdsn.fullstackbackend.repository.ProductRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductRespository productRepository;

    @Autowired
    public ProductService(ProductRespository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDTO productToProductDTO(Product product){
        ProductDTO productViewModel = new ProductDTO();

        productViewModel.setId(product.getId());
        productViewModel.setName(product.getName());

        return productViewModel;
    }

    public Product productDTOToProduct(ProductDTO ProductDTO){
        Product Product = new Product();

        Product.setId(ProductDTO.getId());
        Product.setName(ProductDTO.getName());

        return Product;
    }

    public List<ProductDTO> productToProductDTO(List<Product> productList){
        List<ProductDTO> productViewModelList = new ArrayList<>();
        for (Product product: productList) {
            productViewModelList.add(productToProductDTO(product));
        }
        return productViewModelList;
    }


    public ProductDTO create(ProductDTO product){
        return productToProductDTO(productRepository.save(productDTOToProduct(product)));
    }

    public List<ProductDTO> findAll(){
        return productToProductDTO(productRepository.findAll());
    }

    public ProductDTO findById(Long id){
        return productToProductDTO(productRepository.findById(id)
                .orElseThrow(()->new ProductNotFoundException(id)));
    }

    public ProductDTO update(ProductDTO newProduct){
        if (productRepository.existsById(newProduct.getId())) {
            return productToProductDTO(productRepository.save(productDTOToProduct(newProduct)));
        }
        throw new ProductNotFoundException(newProduct.getId());
    }

    public ProductDTO delete(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(()->new ProductNotFoundException(id));
        productRepository.deleteById(id);
        return productToProductDTO(product);
    }
}
