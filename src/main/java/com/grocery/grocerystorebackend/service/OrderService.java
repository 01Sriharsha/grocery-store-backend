package com.grocery.grocerystorebackend.service;

import com.grocery.grocerystorebackend.dto.OrderDto;
import com.grocery.grocerystorebackend.entity.Customer;
import com.grocery.grocerystorebackend.entity.Order;
import com.grocery.grocerystorebackend.entity.Product;
import com.grocery.grocerystorebackend.repository.CustomerRepository;
import com.grocery.grocerystorebackend.repository.OrderRepository;
import com.grocery.grocerystorebackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public OrderDto saveOrder(Order order, Integer customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        order.setId(UUID.randomUUID().toString().split("-")[0].toUpperCase());
        order.setCustomer(customer);
        order.setDate(LocalDate.now());
        order.setDispatched(false);
        order.setDelivered(false);
        List<Product> products  = new ArrayList<>();
        order.getCartDto().forEach(product -> {
            Product productObj = productRepository.findById(product.getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            products.add(productObj);
        });
        order.setProducts(products);
        Order savedOrder = orderRepository.save(order);
        return this.mapToDto(savedOrder);
    }

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream().map(this::mapToDto).toList();
    }

    public List<OrderDto> getAllOrdersByCustomerId(Integer customerId) {
        return orderRepository.findByCustomerId(customerId).stream().map(this::mapToDto).toList();
    }    

    public List<Product> getAllProductsByOrderId(String orderId) {
        return orderRepository.findProductsByOrderId(orderId);
    }

    public OrderDto updateOrderByAdmin(String orderId , Order order){
        Order existingOrder = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        existingOrder.setDelivered(order.isDelivered());
        existingOrder.setDispatched(order.isDispatched());
        existingOrder.setCancel(order.getCancel());
        existingOrder.getCartDto().forEach(product -> {
            Product productObj = productRepository.findById(product.getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            if(order.isDispatched() && product.getQuantity() != null && Long.parseLong(productObj.getTotalQuantity())>0){
                //calculate remaining quantity from the total quantity of the product
                long remainingQuantity = Long.parseLong(productObj.getTotalQuantity()) - product.getQuantity();
                productObj.setTotalQuantity(String.valueOf(remainingQuantity));
            }
        });
        Order updatedOrder = orderRepository.save(existingOrder);
        return this.mapToDto(updatedOrder);
    }

    public OrderDto updateOrderByCustomer(String orderId , Order order){
        Order existingOrder = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        existingOrder.setAddress(order.getAddress());
        existingOrder.setCity(order.getCity());
        existingOrder.setCancel(order.getCancel());
        Order updatedOrder = orderRepository.save(existingOrder);
        return this.mapToDto(updatedOrder);
    }

    public void deleteOrder(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.delete(order);
    }


    private OrderDto mapToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getId());
        orderDto.setDate(order.getDate());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setCancel(order.getCancel());
        orderDto.setDispatched(order.isDispatched());
        orderDto.setDelivered(order.isDelivered());
        orderDto.setCity(order.getCity());
        orderDto.setAddress(order.getAddress());
        orderDto.setCustomerId(order.getCustomer().getId());
        orderDto.setCustomerName(order.getCustomer().getName());
        orderDto.setQuantity(order.getQuantity());
        orderDto.setPhone(order.getPhone());

        List<Map<String, Object>> products = order.getProducts().stream().map(product -> {
            Map<String, Object> productMap = new HashMap<>();
            productMap.put("id", product.getId());
            productMap.put("name", product.getName());
            productMap.put("brand", product.getBrand());
            productMap.put("price",
                    product.getPricePerKg() == null
                            ? product.getPricePerPiece()
                            : product.getPricePerKg()
            );
            return productMap;
        }).toList();

        orderDto.setProducts(products);

        return orderDto;
    }

}
