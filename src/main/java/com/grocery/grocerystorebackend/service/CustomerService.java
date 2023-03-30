package com.grocery.grocerystorebackend.service;


import com.grocery.grocerystorebackend.entity.Customer;
import com.grocery.grocerystorebackend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer getSingleCustomer(Integer customerId){
        return customerRepository.findById(customerId)
                .orElseThrow(()->new RuntimeException("User not found"));
    }

    public Customer createCustomer(Customer customer){
        if(customerRepository.existsByEmail(customer.getEmail())){
            throw new RuntimeException("Provided email already exists!!");
        }
        customer.setUser("customer");
        return customerRepository.save(customer);
    }

    public Customer upadteCustomer(Integer customerId , Customer customer){
        var existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(()->new RuntimeException("User not found"));
        existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setCity(customer.getCity());
        existingCustomer.setPhone(customer.getPhone());
        return customerRepository.save(existingCustomer);
    }

    public void deleteCustomer(Integer customerId){
        var customer = customerRepository.findById(customerId)
                .orElseThrow(()->new RuntimeException("User not found"));
        customerRepository.delete(customer);
    }
}
