package com.grocery.grocerystorebackend.repository;

import com.grocery.grocerystorebackend.entity.Image;
import com.grocery.grocerystorebackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image , Integer> {

    List<Image> findByProduct(Product product);
}
