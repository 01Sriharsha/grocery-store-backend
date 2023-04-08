package com.grocery.grocerystorebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartDto {

    private Integer cartId;

    private Long quantity;

    private String id;

    private String name;

    private String price;

    private String brand;
    
    private Integer customerId;
}
