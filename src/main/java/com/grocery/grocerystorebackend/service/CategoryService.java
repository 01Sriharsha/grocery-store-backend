package com.grocery.grocerystorebackend.service;


import com.grocery.grocerystorebackend.entity.Category;
import com.grocery.grocerystorebackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();

    }

    public Category getSingleCategory(Integer categoryId){
        return categoryRepository.findById(categoryId)
                .orElseThrow(()->new RuntimeException("category not found"));

    }

    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    public void deleteCategory(Integer categoryId){
        var category = categoryRepository.findById((categoryId))
                .orElseThrow(()->new RuntimeException("category not found"));
        categoryRepository.delete(category);
    }
}
