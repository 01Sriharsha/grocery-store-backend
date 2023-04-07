package com.grocery.grocerystorebackend.service;

import com.grocery.grocerystorebackend.entity.Cart;
import com.grocery.grocerystorebackend.entity.Customer;
import com.grocery.grocerystorebackend.entity.Product;
import com.grocery.grocerystorebackend.repository.CartRepository;
import com.grocery.grocerystorebackend.repository.CustomerRepository;
import com.grocery.grocerystorebackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductService productService;

    public Cart createOrUpdateCart(Integer customerId, String productId, Cart cart) {
        Optional<Cart> optionalCart = cartRepository.findByCustomerIdAndProductId(customerId, productId);
        if (optionalCart.isPresent()) {
            Cart cartItem = optionalCart.get();
            cartItem.setQuantity(cartItem.getQuantity() + cart.getQuantity());
            return cartRepository.save(cartItem);
        } else {
            Product product = productService.getSingleProduct(productId);
            Customer customer = new Customer();
            customer.setId(customerId);
            cart.setCustomer(customer);
            cart.setProduct(product);
            cart.setQuantity(cart.getQuantity());
            return cartRepository.save(cart);
        }
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public List<Cart> getCartsByCustomerId(Integer customerId) {
        return cartRepository.findByCustomerId(customerId);
    }

    public Optional<Cart> getCartByCustomerIdAndProductId(Integer customerId, String productId) {
        return cartRepository.findByCustomerIdAndProductId(customerId, productId);
    }

    public Cart updateCart(Integer cartItemId, Cart cart) {
        Optional<Cart> optionalCart = cartRepository.findById(cartItemId);
        if (optionalCart.isPresent()) {
            Cart cartItem = optionalCart.get();
            cartItem.setQuantity(cart.getQuantity());
            return cartRepository.save(cartItem);
        } else {
            throw new RuntimeException("Cart item with ID " + cartItemId + " not found.");
        }
    }

    public void deleteCart(Integer cartItemId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartItemId);
        if (optionalCart.isPresent()) {
            cartRepository.delete(optionalCart.get());
        } else {
            throw new RuntimeException("Cart item with ID " + cartItemId + " not found.");
        }
    }


}
