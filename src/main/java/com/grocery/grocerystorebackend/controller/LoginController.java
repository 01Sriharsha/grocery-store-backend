package com.grocery.grocerystorebackend.controller;

import com.grocery.grocerystorebackend.dto.LoginDto;
import com.grocery.grocerystorebackend.repository.AdminRepository;
import com.grocery.grocerystorebackend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/v1")
public class LoginController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) {
        //admin login
        if (loginDto.getUserType().equals("admin")) {
            var admin = adminRepository.findById(Integer.parseInt(loginDto.getUserId()))
                    .orElseThrow(() -> new RuntimeException("User not found"));
            if (!admin.getPassword().equals(loginDto.getPassword())) {
                throw new RuntimeException("Invalid password");
            }
            return new ResponseEntity<>("admin", HttpStatus.OK);
        }

        //Customer login
        else if (loginDto.getUserType().equals("customer")) {
            var customer = customerRepository.findByEmail(loginDto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            if (!customer.getPassword().equals(loginDto.getPassword())) {
                throw new RuntimeException("Invalid password");
            }
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            throw new RuntimeException("Select user Type");
        }
    }
}
