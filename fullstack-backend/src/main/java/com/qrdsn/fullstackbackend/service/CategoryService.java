package com.qrdsn.fullstackbackend.service;

import com.qrdsn.fullstackbackend.exception.CategoryNotFoundException;
import com.qrdsn.fullstackbackend.model.Category;
import com.qrdsn.fullstackbackend.model.dto.CategoryDTO;
import com.qrdsn.fullstackbackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDTO categoryToCategoryDTO(Category category){
        CategoryDTO categoryViewModel = new CategoryDTO();

        categoryViewModel.setId(category.getId());
        categoryViewModel.setName(category.getName());

        return categoryViewModel;
    }

    public Category categoryDTOToCategory(CategoryDTO CategoryDTO){
        Category Category = new Category();

        Category.setId(CategoryDTO.getId());
        Category.setName(CategoryDTO.getName());

        return Category;
    }

    public List<CategoryDTO> categoryToCategoryDTO(List<Category> categoryList){
        List<CategoryDTO> categoryViewModelList = new ArrayList<>();
        for (Category category: categoryList) {
            categoryViewModelList.add(categoryToCategoryDTO(category));
        }
        return categoryViewModelList;
    }

    public CategoryDTO create(CategoryDTO category){
        return categoryToCategoryDTO(categoryRepository.save(categoryDTOToCategory(category)));
    }

    public List<CategoryDTO> findAll(){
        return categoryToCategoryDTO(categoryRepository.findAll());
    }

    public CategoryDTO findById(Long id){
        return categoryToCategoryDTO(categoryRepository.findById(id)
                .orElseThrow(()->new CategoryNotFoundException(id)));
    }

    public CategoryDTO update(CategoryDTO newCategory){
        if (categoryRepository.existsById(newCategory.getId())) {
            return categoryToCategoryDTO(categoryRepository.save(categoryDTOToCategory(newCategory)));
        }
        throw new CategoryNotFoundException(newCategory.getId());
    }

    public CategoryDTO delete(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(()->new CategoryNotFoundException(id));
        categoryRepository.deleteById(id);
        return categoryToCategoryDTO(category);
    }
}