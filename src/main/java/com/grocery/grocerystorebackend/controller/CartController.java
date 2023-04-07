package com.grocery.grocerystorebackend.controller;


import com.grocery.grocerystorebackend.entity.Cart;
import com.grocery.grocerystorebackend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/customers/{customerId}/products/{productId}/cart")
    public ResponseEntity<?> saveCartItemCount(
            @RequestBody Cart cart,
            @PathVariable Integer customerId,
            @PathVariable String productId
    ) {
        return new ResponseEntity<>(cartService.saveItemCount(customerId, productId, cart) , HttpStatus.CREATED);
    }

    @GetMapping("/customers/{customerId}/cart")
    public ResponseEntity<?> getAllDetailsFromCartOfCustomer(@PathVariable Integer customerId){
        return new ResponseEntity<>(cartService.getAllCartDetailsBYCustomer(customerId) , HttpStatus.OK);
    }

}
