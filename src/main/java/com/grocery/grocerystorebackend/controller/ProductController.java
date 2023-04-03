package com.grocery.grocerystorebackend.controller;

import com.grocery.grocerystorebackend.entity.Product;
import com.grocery.grocerystorebackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<?> retrieveAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts() , HttpStatus.OK);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<?> retrieveSingleProduct(String productId){
        return new ResponseEntity<>(productService.getSingleProduct(productId) , HttpStatus.OK);
    }

    @PostMapping("/categories/{categoryId}/subcategories/{subcategoryId}/products")
    public ResponseEntity<?> createNewProduct(
            @PathVariable Integer categoryId,
            @PathVariable Integer subcategoryId,
            @RequestBody Product product
    ){
        productService.createProduct(categoryId , subcategoryId , product);
        return new ResponseEntity<>("Product added successfully!!", HttpStatus.CREATED);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable String productId,@RequestBody Product product){
        productService.updateProduct(productId , product);
        return new ResponseEntity<>("Product updated successfully!!" , HttpStatus.CREATED);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable String productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>("Product removed successfully!!" , HttpStatus.OK);
    }

    //get all products by category
    @GetMapping("/categories/{categoryId}/products")
    public ResponseEntity<?> retrieveAllProductsByCategory(@PathVariable Integer categoryId){
        return new ResponseEntity<>(productService.getAllProductsByCategory(categoryId) , HttpStatus.OK);
    }

    //get all products by sub category
    @GetMapping("/subcategories/{subCategoryId}/products")
    public ResponseEntity<?> retrieveAllProductsBySubCategory(@PathVariable Integer subCategoryId){
        return new ResponseEntity<>(productService.getAllProductsBySubCategory(subCategoryId) , HttpStatus.OK);
    }

}
