package com.qrdsn.fullstackbackend.service;

import com.qrdsn.fullstackbackend.exception.NotFoundException;
import com.qrdsn.fullstackbackend.model.Category;
import com.qrdsn.fullstackbackend.model.Product;
import com.qrdsn.fullstackbackend.model.dto.CategoryDTO;
import com.qrdsn.fullstackbackend.model.dto.ProductDTO;
import com.qrdsn.fullstackbackend.repository.CategoryRepository;
import com.qrdsn.fullstackbackend.repository.ProductRespository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class InventoryService {
    private final ProductRespository productRepository;
    private final CategoryRepository categoryRepository;


    @Autowired
    public InventoryService(ProductRespository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    ModelMapper modelMapper = new ModelMapper();

    public ProductDTO createProduct(ProductDTO product){
        return modelMapper.map(productRepository.save(modelMapper.map(product, Product.class)), ProductDTO.class);
    }

    public List<ProductDTO> findAllProducts(){
        return Arrays.asList(modelMapper.map(productRepository.findAll(), ProductDTO[].class));
    }

    public List<ProductDTO> findAllProductsByCategoryId(Long categoryId){
        return Arrays.asList(modelMapper.map(productRepository.findAllByCategoryId(categoryId), ProductDTO[].class));
    }

    public ProductDTO findProduct(Long id){
        return modelMapper.map(productRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Could not find product " + id)), ProductDTO.class);
    }


    public ProductDTO updateProduct(ProductDTO newProduct){
        if (productRepository.existsById(newProduct.getId())) {
            return modelMapper.map(productRepository.save(modelMapper.map(newProduct, Product.class)), ProductDTO.class);
        }
        throw new NotFoundException("Could not find product " + newProduct.getId());
    }

    public ProductDTO deleteProduct(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Could not find product " + id));
        productRepository.deleteById(id);
        return modelMapper.map(product, ProductDTO.class);
    }

    public CategoryDTO createCategory(CategoryDTO category){
        return modelMapper.map(categoryRepository.save(modelMapper.map(category, Category.class)), CategoryDTO.class);
    }

    public List<CategoryDTO> findAllCategories(){
        return Arrays.asList(modelMapper.map(categoryRepository.findAll(), CategoryDTO[].class));
    }

    public CategoryDTO findCategory(Long id){
        return modelMapper.map(categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not find category " + id)), CategoryDTO.class);
    }

    public CategoryDTO updateCategory(CategoryDTO newCategory){
        if (categoryRepository.existsById(newCategory.getId())) {
            return modelMapper.map(categoryRepository.save(modelMapper.map(newCategory, Category.class)), CategoryDTO.class);
        }
        throw new NotFoundException("Could not find category " + newCategory.getId());
    }

    public CategoryDTO deleteCategory(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Could not find category " + id));
        categoryRepository.deleteById(id);
        return modelMapper.map(category, CategoryDTO.class);
    }
}
