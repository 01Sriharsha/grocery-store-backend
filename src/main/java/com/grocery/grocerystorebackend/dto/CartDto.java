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

    private Integer id;

    private String quantity;

    private String productId;

    private String productName;
    
    private Integer customerId;
}
