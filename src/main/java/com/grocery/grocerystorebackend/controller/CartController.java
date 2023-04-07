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
            @RequestBody Cart cart,
            @PathVariable Integer customerId,
            @PathVariable String productId
    ) {
        return new ResponseEntity<>(cartService.createOrUpdateCart(customerId, productId, cart), HttpStatus.CREATED);
    }

    @GetMapping("/customers/{customerId}/cart")
    public ResponseEntity<?> getAllCartItemsByCustomerId(@PathVariable Integer customerId) {
        List<Cart> cartItems = cartService.getCartsByCustomerId(customerId);
        Stream<CartDto> cartDtoStream = cartItems.stream().map(CartController::mapToDto);
        return new ResponseEntity<>(cartDtoStream, HttpStatus.OK);
    }

    private static CartDto mapToDto(Cart item) {
        return new CartDto(
                item.getId(),
                item.getQuantity(),
                item.getProduct().getId(),
                item.getProduct().getName(),
                item.getCustomer().getId()
        );
    }

    @GetMapping("/customers/{customerId}/products/{productId}/cart")
    public ResponseEntity<?> getAllCartItemsProductId(
            @PathVariable Integer customerId,
            @PathVariable String productId
    ) {
        Optional<Cart> optionalCart = cartService.getCartByCustomerIdAndProductId(customerId, productId);
        if (optionalCart.isEmpty()) throw new RuntimeException("Product Not Found");
        else {
            return new ResponseEntity<>(optionalCart.get(), HttpStatus.OK);
        }
    }

    @DeleteMapping("/cart/{cartItemId}")
    public ResponseEntity<?> deleteCartItem(@PathVariable Integer cartItemId) {
        cartService.deleteCart(cartItemId);
        return new ResponseEntity<>("Cart Item removed successfully!!", HttpStatus.OK);
    }

}
