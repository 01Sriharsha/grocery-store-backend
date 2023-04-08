package com.grocery.grocerystorebackend.controller;


import com.grocery.grocerystorebackend.dto.CartDto;
import com.grocery.grocerystorebackend.entity.Cart;
import com.grocery.grocerystorebackend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/customers/{customerId}/products/{productId}/cart")
    public ResponseEntity<?> createOrUpdateCartItems(
            @PathVariable Integer customerId,
            @PathVariable String productId
    ) {
        return new ResponseEntity<>(cartService.createOrUpdateCart(customerId, productId), HttpStatus.CREATED);
    }

    @GetMapping("/customers/{customerId}/cart")
    public ResponseEntity<?> getAllCartItemsByCustomerId(@PathVariable Integer customerId) {
        List<CartDto> cartItems = cartService.getCartsByCustomerId(customerId);
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

    @GetMapping("/customers/{customerId}/products/{productId}/cart")
    public ResponseEntity<?> getAllCartItemsByProductId(
            @PathVariable Integer customerId,
            @PathVariable String productId
    ) {
        Optional<Cart> optionalCart = cartService.getCartByCustomerIdAndProductId(customerId, productId);
        if (optionalCart.isEmpty()) throw new RuntimeException("Product Not Found");
        else {
            return new ResponseEntity<>(optionalCart.get(), HttpStatus.OK);
        }
    }

    @PutMapping("/cart/{cartId}/decrement")
    public ResponseEntity<?> decrementCartQuantity(@PathVariable Integer cartId){
        cartService.decrementCartQuantity(cartId);
        return new ResponseEntity<>("Cart Item decremented!!" , HttpStatus.OK);
    }

    @DeleteMapping("/customers/{customerId}/cart")
    public ResponseEntity<?> deleteAllCartItemsByCustomerId(@PathVariable Integer customerId) {
        cartService.deleteAllCartItemsByCustomerId(customerId);
        return new ResponseEntity<>("Cart Items cleared successfully!!", HttpStatus.OK);
    }

    @DeleteMapping("/cart/{cartItemId}")
    public ResponseEntity<?> deleteCartItem(@PathVariable Integer cartItemId) {
        cartService.deleteCart(cartItemId);
        return new ResponseEntity<>("Cart Item removed successfully!!", HttpStatus.OK);
    }

}
