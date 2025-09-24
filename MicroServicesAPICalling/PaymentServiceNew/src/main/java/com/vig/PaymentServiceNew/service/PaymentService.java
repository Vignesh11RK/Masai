package com.vig.PaymentServiceNew.service;

import com.vig.PaymentServiceNew.dto.PaymentDTO;
import com.vig.PaymentServiceNew.entity.Payment;
import com.vig.PaymentServiceNew.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository repository;

    public Payment createPayment(PaymentDTO dto) {
        Payment payment = new Payment();
        payment.setOrderId(dto.getOrderId());
        payment.setStatus(dto.getStatus());
        return repository.save(payment);
    }

    public PaymentDTO getPaymentByOrderId(Long orderId) {
        return repository.findByOrderId(orderId)
                .map(p -> new PaymentDTO(p.getOrderId(), p.getStatus()))
                .orElseThrow(() -> new PaymentNotFoundException(orderId));
    }
}

