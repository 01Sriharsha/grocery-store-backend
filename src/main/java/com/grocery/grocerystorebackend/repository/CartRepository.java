package com.grocery.grocerystorebackend.repository;

import com.grocery.grocerystorebackend.entity.Cart;
import com.grocery.grocerystorebackend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart , Integer> {


    List<Cart> findByCustomer(Customer customer);

}
