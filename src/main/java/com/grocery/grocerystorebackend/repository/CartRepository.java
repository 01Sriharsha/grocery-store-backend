package com.grocery.grocerystorebackend.repository;

import com.grocery.grocerystorebackend.entity.Cart;
import com.grocery.grocerystorebackend.entity.Customer;
import com.grocery.grocerystorebackend.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart , Integer> {


    Optional<Cart> findByCustomerIdAndProductId(Integer customerId , String productId);

    List<Cart> findByCustomerId(Integer customerId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Cart c WHERE c.customer.id = :customerId")
    void deleteByCustomerId(@Param("customerId") Integer customerId);
}
