package com.grocery.grocerystorebackend.repository;

import com.grocery.grocerystorebackend.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback , Long> {

    List<Feedback> findByCustomerId(Integer customerId);
    List<Feedback> findByProductId(String productId);
}
