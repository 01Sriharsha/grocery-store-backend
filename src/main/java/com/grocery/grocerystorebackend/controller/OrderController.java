package com.grocery.grocerystorebackend.controller;

import com.grocery.grocerystorebackend.entity.Order;
import com.grocery.grocerystorebackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/v1")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/customers/{customerId}/orders")
    public ResponseEntity<?> createOrder(@RequestBody Order order , @PathVariable Integer customerId){
          return new ResponseEntity<>(orderService.saveOrder(order , customerId) , HttpStatus.CREATED);
    }

    @GetMapping("/orders")
    public ResponseEntity<?> createOrder(){
        return new ResponseEntity<>(orderService.getAllOrders() , HttpStatus.OK);
    }

    @GetMapping("/customers/{customerId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable Integer customerId){
        return new ResponseEntity<>(orderService.getAllOrdersByCustomerId(customerId) , HttpStatus.OK);
    }

    @GetMapping("/orders/{orderId}/products")
    public ResponseEntity<?> getAllProductsByOrderId(@PathVariable String  orderId){
        return new ResponseEntity<>(orderService.getAllProductsByOrderId(orderId) , HttpStatus.OK);
    }

    @PutMapping("/orders/{orderId}/admin")
    public ResponseEntity<?> updateOrderByAdmin(@RequestBody Order order , @PathVariable String orderId){
        return new ResponseEntity<>(orderService.updateOrderByAdmin(orderId, order),HttpStatus.CREATED);
    }

    @PutMapping("/orders/{orderId}/customer")
    public ResponseEntity<?> updateOrderByCustomer(@RequestBody Order order , @PathVariable String orderId){
        return new ResponseEntity<>(orderService.updateOrderByCustomer(orderId, order),HttpStatus.CREATED);
    }

    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable String  orderId){
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>("Order deleted successfully!!", HttpStatus.OK);
    }
}
