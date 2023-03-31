package com.grocery.grocerystorebackend.service;


import com.grocery.grocerystorebackend.entity.Category;
import com.grocery.grocerystorebackend.entity.SubCategory;
import com.grocery.grocerystorebackend.repository.CategoryRepository;
import com.grocery.grocerystorebackend.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<SubCategory> getAllSubCategories(){
        return subCategoryRepository.findAll();

    }

    public List<SubCategory> getAllSubCategoriesByCategory(Integer categoryId){
        var category = categoryRepository.findById((categoryId))
                .orElseThrow(()->new RuntimeException("category not found"));
        return subCategoryRepository.findByCategory(category);
    }

    public SubCategory getSingleSubCategory(Integer subCategoryId){
        return subCategoryRepository.findById(subCategoryId)
                .orElseThrow(()->new RuntimeException("SubCategory not found"));

    }

    public SubCategory createSubCategory(Integer categoryId , SubCategory subCategory){
        var category = categoryRepository.findById((categoryId))
                .orElseThrow(()->new RuntimeException("category not found"));
        subCategory.setCategory(category);
        return subCategoryRepository.save(subCategory);
    }

    public SubCategory updateSubCategory(Integer subCategoryId , SubCategory subCategory){
        var existingSubCategory = subCategoryRepository.findById((subCategoryId))
                .orElseThrow(()->new RuntimeException("Sub Category not found"));
        existingSubCategory.setName(subCategory.getName());
        existingSubCategory.setDescription(subCategory.getDescription());
        return subCategoryRepository.save(existingSubCategory);
    }

    public void deleteSubCategory(Integer subCategoryId){
        var subCategory = subCategoryRepository.findById((subCategoryId))
                .orElseThrow(()->new RuntimeException("SubCategory not found"));
        subCategoryRepository.delete(subCategory);
    }
}
