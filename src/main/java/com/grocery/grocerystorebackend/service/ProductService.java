package com.grocery.grocerystorebackend.service;

import com.grocery.grocerystorebackend.entity.Product;
import com.grocery.grocerystorebackend.repository.CategoryRepository;
import com.grocery.grocerystorebackend.repository.ProductRepository;
import com.grocery.grocerystorebackend.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();

    }

    public Product getSingleProduct(String productId){
        return productRepository.findById((productId))
                .orElseThrow(()->new RuntimeException("Product not found"));

    }

    public Product createProduct(Integer categoryId , Integer subCategoryId , Product product){
        var category = categoryRepository.findById((categoryId))
                .orElseThrow(()->new RuntimeException("category not found"));
        var subCategory = subCategoryRepository.findById((subCategoryId))
                .orElseThrow(()->new RuntimeException("SubCategory not found"));
        product.setId(UUID.randomUUID().toString().split("-")[0].toUpperCase());
        product.setCategory(category);
        product.setSubCategory(subCategory);
        return productRepository.save(product);
    }

    public Product updateProduct(String productId , Product product){
        var existingProduct = productRepository.findById((productId))
                .orElseThrow(()->new RuntimeException("Product not found"));
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setBrand(product.getBrand());
        existingProduct.setMfdDate(product.getMfdDate());
        existingProduct.setExpDate(product.getExpDate());
        return productRepository.save(existingProduct);
    }


    public void deleteProduct(String productId){
        var product = productRepository.findById((productId))
                .orElseThrow(()->new RuntimeException("Product not found"));
        productRepository.delete(product);
    }



    public List<Product> getAllProductsByCategory(Integer categoryId){
        var category = categoryRepository.findById((categoryId))
                .orElseThrow(()->new RuntimeException("category not found"));
        return productRepository.findByCategory(category);
    }

    public List<Product> getAllProductsBySubCategory(Integer subCategoryId){
        var subCategory = subCategoryRepository.findById((subCategoryId))
                .orElseThrow(()->new RuntimeException("SubCategory not found"));
        return productRepository.findBySubCategory(subCategory);
    }

}
