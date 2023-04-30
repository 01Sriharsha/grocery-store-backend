package com.grocery.grocerystorebackend.service;

import com.grocery.grocerystorebackend.entity.Customer;
import com.grocery.grocerystorebackend.entity.ProductRequest;
import com.grocery.grocerystorebackend.repository.CustomerRepository;
import com.grocery.grocerystorebackend.repository.ProductRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ProductRequestService {

    @Autowired
    private ProductRequestRepository productRequestRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public ProductRequest createProductRequest(ProductRequest productRequest, Integer customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        productRequest.setId(UUID.randomUUID().toString().split("-")[0].toUpperCase());
        productRequest.setDate(LocalDate.now());
        productRequest.setCustomer(customer);
        return productRequestRepository.save(productRequest);
    }

    public List<ProductRequest> getAllProductRequests() {
        return productRequestRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }

    public List<ProductRequest> getAllProductRequestsByCustomer(Integer customerId) {
        return productRequestRepository
                .findByCustomerId(customerId, Sort.by(Sort.Direction.DESC, "date"));
    }

    public ProductRequest getSingleProductRequest(String requestId) {
        return productRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
    }

    public ProductRequest updateProductRequest(String requestId, ProductRequest productRequest) {
        ProductRequest existingRequest = productRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        existingRequest.setReply(productRequest.getReply());
        return productRequestRepository.save(existingRequest);
    }

    public void deleteProductRequest(String requestId) {
        ProductRequest request = productRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        if(request.getReply()!= null){
            throw new RuntimeException("Cannot perform action!! Request already got replied...!");
        }
        productRequestRepository.delete(request);
    }
}
