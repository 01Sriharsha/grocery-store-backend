package com.grocery.grocerystorebackend.controller;


import com.grocery.grocerystorebackend.entity.Category;
import com.grocery.grocerystorebackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/v1")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<?> retrieveAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories() , HttpStatus.OK);
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<?> retrieveSingleCategory(@PathVariable Integer categoryId){
        return new ResponseEntity<>(categoryService.getSingleCategory(categoryId) , HttpStatus.OK);
    }

    @PostMapping("/categories")
    public ResponseEntity<?> createCategory(@RequestBody Category category){
        return new ResponseEntity<>(categoryService.createCategory(category) , HttpStatus.CREATED);
    }

    @PutMapping("/categories/{categoryId}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer categoryId , @RequestBody Category category){
        return new ResponseEntity<>(categoryService.updateCategory(categoryId , category) , HttpStatus.CREATED);
    }

    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>("Category removed successfully!!", HttpStatus.OK);
    }
}
