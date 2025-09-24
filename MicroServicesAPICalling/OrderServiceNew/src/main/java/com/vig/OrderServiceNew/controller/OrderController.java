package com.vig.OrderServiceNew.controller;

import com.vig.OrderServiceNew.dto.CreateOrderRequest;
import com.vig.OrderServiceNew.dto.OrderDTO;
import com.vig.OrderServiceNew.entity.Order;
import com.vig.OrderServiceNew.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody CreateOrderRequest request) {
        Order order = service.createOrder(request);
        return new ResponseEntity<>(new OrderDTO(order.getId(), order.getProductName(), order.getQuantity(), null), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/rest")
    public ResponseEntity<OrderDTO> getOrderWithRestTemplate(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOrderWithPaymentRestTemplate(id));
    }

    @GetMapping("/{id}/webclient")
    public ResponseEntity<OrderDTO> getOrderWithWebClient(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOrderWithPaymentWebClient(id));
    }

    @GetMapping("/{id}/feign")
    public ResponseEntity<OrderDTO> getOrderWithFeign(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOrderWithPaymentFeign(id));
    }
}

