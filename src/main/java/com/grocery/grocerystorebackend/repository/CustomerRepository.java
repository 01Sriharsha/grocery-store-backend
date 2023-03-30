package com.grocery.grocerystorebackend.repository;

import com.grocery.grocerystorebackend.entity.Admin;
import com.grocery.grocerystorebackend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    boolean existsByEmail(String email);

    Optional<Customer> findByEmail(String email);
}
