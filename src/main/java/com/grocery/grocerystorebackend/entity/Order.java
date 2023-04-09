package com.grocery.grocerystorebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grocery.grocerystorebackend.dto.CartDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orders")
public class Order {

    @Id
    private String id;

    private LocalDate date;

    private Long quantity;

    private String totalPrice;

    private String address;

    private String phone;

    private String city;

    private String cancel;

    @Transient
    private List<CartDto> cartDto;

    private boolean isDispatched;

    private boolean isDelivered;

    @ManyToMany
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Customer customer;
}
