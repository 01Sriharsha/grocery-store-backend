package com.grocery.grocerystorebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDto {

    private String orderId;

    private LocalDate date;

    private Long quantity;

    private String address;

    private String city;

    private String phone;

    private String totalPrice;

    private boolean isDispatched;

    private boolean isDelivered;

    private String cancel;

    private List<Map<String, Object>> products;

    private Integer customerId;
}
