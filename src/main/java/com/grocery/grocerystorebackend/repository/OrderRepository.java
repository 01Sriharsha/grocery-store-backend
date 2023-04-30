package com.grocery.grocerystorebackend.repository;

import com.grocery.grocerystorebackend.entity.Order;
import com.grocery.grocerystorebackend.entity.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order , String> {

    List<Order> findByCustomerId(Integer customerId , Sort sort);

    @Query("SELECT o.products FROM Order o WHERE o.id = :orderId")
    List<Product> findProductsByOrderId(@Param("orderId") String orderId);
}
