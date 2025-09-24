package com.vig.OrderServiceNew.client;

import com.vig.OrderServiceNew.dto.PaymentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "payment-service", url = "${payment.service.url}")
public interface PaymentFeignClient {
    @GetMapping("/{orderId}")
    PaymentDTO getPaymentStatus(@PathVariable("orderId") Long orderId);
}

