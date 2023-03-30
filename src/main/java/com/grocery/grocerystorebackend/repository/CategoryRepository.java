package com.grocery.grocerystorebackend.repository;

import com.grocery.grocerystorebackend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category , Integer> {
}
