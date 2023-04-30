package com.grocery.grocerystorebackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRequest {

    @Id
    private String id;

    private String name;

    private String brand;

    private LocalDate date;

    private String message;

    private String reply;

    @ManyToOne
    private Customer customer;
}
