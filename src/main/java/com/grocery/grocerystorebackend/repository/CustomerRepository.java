package com.grocery.grocerystorebackend.repository;

import com.grocery.grocerystorebackend.entity.Admin;
import com.grocery.grocerystorebackend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByEmail(String email);
}
