package com.grocery.grocerystorebackend.repository;

import com.grocery.grocerystorebackend.entity.ProductRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRequestRepository extends JpaRepository<ProductRequest , String> {

    List<ProductRequest> findByCustomerId(Integer id , Sort sort);
}
