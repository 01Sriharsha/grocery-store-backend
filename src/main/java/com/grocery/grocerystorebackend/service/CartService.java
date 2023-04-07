package com.grocery.grocerystorebackend.service;

import com.grocery.grocerystorebackend.entity.Cart;
import com.grocery.grocerystorebackend.repository.CartRepository;
import com.grocery.grocerystorebackend.repository.CustomerRepository;
import com.grocery.grocerystorebackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    public Cart saveItemCount(Integer customerId, String productId, Cart cart) {
        var customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        var product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        cart.setCustomer(customer);
        cart.setProduct(product);
        return cartRepository.save(cart);
    }

    public List<Cart> getAllCartDetailsBYCustomer(Integer customerId) {
        var customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return cartRepository.findByCustomer(customer);
    }


}
