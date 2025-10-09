package com.vig.PaymentService.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @GetMapping("/{id}")
    public Map<String, Object> getPayment(@PathVariable("id") int id) {
        Map<String, Object> response = new HashMap<>();
        response.put("paymentId", id);
        response.put("paymentStatus", "SUCCESS");
        response.put("message", "From Payment Service Instance");
        return response;
    }
}
