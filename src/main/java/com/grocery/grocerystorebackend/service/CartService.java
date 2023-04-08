package com.grocery.grocerystorebackend.service;

import com.grocery.grocerystorebackend.dto.CartDto;
import com.grocery.grocerystorebackend.entity.Cart;
import com.grocery.grocerystorebackend.entity.Customer;
import com.grocery.grocerystorebackend.entity.Product;
import com.grocery.grocerystorebackend.repository.CartRepository;
import com.grocery.grocerystorebackend.repository.CustomerRepository;
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

    public Cart createOrUpdateCart(Integer customerId, String productId) {
        Optional<Cart> optionalCart = cartRepository.findByCustomerIdAndProductId(customerId, productId);
        if (optionalCart.isPresent()) {
            Cart cartItem = optionalCart.get();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            return cartRepository.save(cartItem);
        } else {
            Product product = productService.getSingleProduct(productId);
            Customer customer = new Customer();
            customer.setId(customerId);
            Cart cart = new Cart();
            cart.setCustomer(customer);
            cart.setProduct(product);
            cart.setQuantity(cart.getQuantity() + 1);
            return cartRepository.save(cart);
        }
    }

    public List<CartDto> getAllCarts() {
        return cartRepository.findAll().stream().map(item->this.mapToDto(item)).toList();
    }

    public List<CartDto> getCartsByCustomerId(Integer customerId) {
        List<Cart> cartItems = cartRepository.findByCustomerId(customerId);
        return cartItems.stream().map(item->this.mapToDto(item)).toList();
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


    public void decrementCartQuantity(Integer cartId){
        var cartItem = cartRepository.findById(cartId)
                .orElseThrow(()->new RuntimeException("Item Not Found"));
        if(cartItem.getQuantity() <= 1){
            return;
        }
        cartItem.setQuantity(cartItem.getQuantity()-1);
        cartRepository.save(cartItem);
    }

    public void deleteCart(Integer cartItemId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartItemId);
        if (optionalCart.isPresent()) {
            cartRepository.delete(optionalCart.get());
        } else {
            throw new RuntimeException("Cart item with ID " + cartItemId + " not found.");
        }
    }

    public void deleteAllCartItemsByCustomerId(Integer customerId){
        cartRepository.deleteByCustomerId(customerId);
    }

    private CartDto mapToDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setCartId(cart.getId());
        cartDto.setQuantity(cart.getQuantity());
        cartDto.setId(cart.getProduct().getId());
        cartDto.setName(cart.getProduct().getName());
        cartDto.setBrand(cart.getProduct().getBrand());
        cartDto.setPrice(
                cart.getProduct().getPricePerKg()==null
                        ? cart.getProduct().getPricePerPiece()
                        : cart.getProduct().getPricePerKg()
        );
        cartDto.setCustomerId(cart.getCustomer().getId());
        return cartDto;
    }


}
