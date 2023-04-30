package com.grocery.grocerystorebackend.controller;

import com.grocery.grocerystorebackend.entity.ProductRequest;
import com.grocery.grocerystorebackend.service.ProductRequestService;
import org.aspectj.weaver.ResolvedPointcutDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/v1")
public class ProductRequestController {

    @Autowired
    private ProductRequestService productRequestService;

    @PostMapping("/customers/{customerId}/requests")
    public ResponseEntity<?> createProductRequest(@RequestBody ProductRequest productRequest, @PathVariable Integer customerId) {
        return new ResponseEntity<>(productRequestService.createProductRequest(productRequest, customerId), HttpStatus.CREATED);
    }

    @GetMapping("/customers/{customerId}/requests")
    public ResponseEntity<?> retrieveAllProductRequestsByCustomer(@PathVariable Integer customerId) {
        return new ResponseEntity<>(productRequestService.getAllProductRequestsByCustomer(customerId), HttpStatus.OK);
    }

    @GetMapping("/requests")
    public ResponseEntity<?> retrieveAllProductRequests() {
        return new ResponseEntity<>(productRequestService.getAllProductRequests(), HttpStatus.OK);
    }

    @GetMapping("/requests/{requestId}")
    public ResponseEntity<?> retrieveSingleProductRequest(@PathVariable String requestId) {
        return new ResponseEntity<>(productRequestService.getSingleProductRequest(requestId), HttpStatus.OK);
    }

    @PutMapping("/requests/{requestId}")
    public ResponseEntity<?> updateProductRequest(@PathVariable String requestId, @RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(productRequestService.updateProductRequest(requestId, productRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/requests/{requestId}")
    public ResponseEntity<?> deleteProductRequest(@PathVariable String requestId) {
        productRequestService.deleteProductRequest(requestId);
        return new ResponseEntity<>("Product request removed successfully!!", HttpStatus.OK);
    }
}
