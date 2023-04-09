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
public class Feedback {

    @Id
    private Long id;

    private String message;

    private String reply;

    private LocalDate date;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Customer customer;
}
