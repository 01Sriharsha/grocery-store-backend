package com.grocery.grocerystorebackend.repository;

import com.grocery.grocerystorebackend.entity.Category;
import com.grocery.grocerystorebackend.entity.Product;
import com.grocery.grocerystorebackend.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product , String> {

    List<Product> findByCategory(Category category);
    List<Product> findBySubCategory(SubCategory subCategory);


}
