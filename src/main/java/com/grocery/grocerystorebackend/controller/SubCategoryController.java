package com.grocery.grocerystorebackend.controller;


import com.grocery.grocerystorebackend.entity.Category;
import com.grocery.grocerystorebackend.entity.SubCategory;
import com.grocery.grocerystorebackend.service.CategoryService;
import com.grocery.grocerystorebackend.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/v1")
public class SubCategoryController {

    @Autowired
    private SubCategoryService subCategoryService;

    @GetMapping("/subcategories")
    public ResponseEntity<?> retrieveAllSubCategories(){
        return new ResponseEntity<>(subCategoryService.getAllSubCategories() , HttpStatus.OK);
    }

    @GetMapping("/categories/{categoryId}/subcategories")
    public ResponseEntity<?> retrieveAllSubCategoriesByCategory(@PathVariable Integer categoryId){
        return new ResponseEntity<>(subCategoryService.getAllSubCategoriesByCategory(categoryId) , HttpStatus.OK);
    }

    @GetMapping("/subcategories/{subCategoryId}")
    public ResponseEntity<?> retrieveSingleCategory(@PathVariable Integer subCategoryId){
        return new ResponseEntity<>(subCategoryService.getSingleSubCategory(subCategoryId) , HttpStatus.OK);
    }

    @PostMapping("/categories/{categoryId}/subcategories")
    public ResponseEntity<?> createCategory(@PathVariable Integer categoryId , @RequestBody SubCategory subCategory){
        return new ResponseEntity<>(subCategoryService.createSubCategory(categoryId , subCategory) , HttpStatus.CREATED);
    }

    @PutMapping("/subcategories/{subCategoryId}")
    public ResponseEntity<?> updateSubCategory(@PathVariable Integer subCategoryId , @RequestBody SubCategory subCategory){
        return new ResponseEntity<>(subCategoryService.updateSubCategory(subCategoryId, subCategory) , HttpStatus.CREATED);
    }

    @DeleteMapping("/subcategories/{subCategoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer subCategoryId){
        subCategoryService.deleteSubCategory(subCategoryId);
        return new ResponseEntity<>("Sub Category removed successfully!!", HttpStatus.OK);
    }
}
