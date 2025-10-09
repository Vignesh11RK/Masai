package com.vig.OrderService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{id}")
    public Map<String, Object> getOrder(@PathVariable("id") int id) {
        String paymentServiceUrl = "http://payment-service/payments/" + id;

        Map<String, Object> paymentResponse = restTemplate.getForObject(paymentServiceUrl, Map.class);

        Map<String, Object> orderResponse = new HashMap<>();
        orderResponse.put("orderId", id);
        orderResponse.put("productName", "Laptop");
        orderResponse.put("paymentStatus", paymentResponse != null ? paymentResponse.get("paymentStatus") : "UNKNOWN");

        return orderResponse;
    }
}
