package com.grocery.grocerystorebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String phone;

    @Column(unique = true)
    private String email;

    private String password;

    private String city;

    private String address;

    private String user;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "customer")
    private List<Feedback> feedbacks = new ArrayList<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "customer")
    private List<ProductRequest> productRequests = new ArrayList<>();
}
