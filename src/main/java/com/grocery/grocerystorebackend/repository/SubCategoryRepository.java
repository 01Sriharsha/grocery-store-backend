package com.grocery.grocerystorebackend.repository;

import com.grocery.grocerystorebackend.entity.Category;
import com.grocery.grocerystorebackend.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {

    List<SubCategory> findByCategory(Category category);
}
