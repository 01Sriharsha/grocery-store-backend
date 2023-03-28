package com.grocery.grocerystorebackend.controller;


import com.grocery.grocerystorebackend.entity.Customer;
import com.grocery.grocerystorebackend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/v1")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<?> retrieveAllCustomers(){
        return new ResponseEntity<>(customerService.getAllCustomers() , HttpStatus.OK);
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<?> retrieveSingleCustomer(@PathVariable Integer customerId){
        return new ResponseEntity<>(customerService.getSingleCustomer(customerId) , HttpStatus.OK);
    }

    @PostMapping("/customers")
    public ResponseEntity<?> retrieveSingleCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>(customerService.createCustomer(customer) , HttpStatus.CREATED);
    }

    @PutMapping("/customers/{customerId}")
    public ResponseEntity<?> updateCustomer(@PathVariable Integer customerId,@RequestBody Customer customer){
        return new ResponseEntity<>(customerService.upadteCustomer(customerId , customer) , HttpStatus.CREATED);
    }

    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<?> removeCustomer(@PathVariable Integer customerId){
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>("Customer removed successfully!!" , HttpStatus.OK);
    }

}
