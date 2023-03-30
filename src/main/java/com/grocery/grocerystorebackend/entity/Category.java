package com.grocery.grocerystorebackend.entity;


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
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    @OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "category")
    private List<SubCategory> subCategories  = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "category")
    private List<Product> products = new ArrayList<>();
}
