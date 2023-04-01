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
public class Product {

    @Id
    private String id;

    private String name;

    private String quantity;

    private String mfdDate;

    private String expDate;

    private String price;

    private String brand;

    @Column(length = 5000)
    private String description;

    private String discount;

    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    private SubCategory subCategory;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "product")
    private List<Image> images = new ArrayList<>();
}
