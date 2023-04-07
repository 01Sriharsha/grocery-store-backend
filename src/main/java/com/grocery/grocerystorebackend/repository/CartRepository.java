package com.grocery.grocerystorebackend.repository;

import com.grocery.grocerystorebackend.entity.Cart;
import com.grocery.grocerystorebackend.entity.Customer;
import com.grocery.grocerystorebackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart , Integer> {


    List<Cart> findByCustomer(Customer customer);

    Optional<Cart> findByCustomerAndProduct(Customer customer , Product product);


    Optional<Cart> findByCustomerIdAndProductId(Integer customerId , String productId);

    List<Cart> findByCustomerId(Integer customerId);
}
