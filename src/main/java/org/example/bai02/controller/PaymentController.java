package org.example.bai02.controller;

import lombok.RequiredArgsConstructor;
import org.example.bai02.model.dto.request.PaymentDTO;
import org.example.bai02.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/pay")
    public String pay(@RequestBody PaymentDTO request) {

        return paymentService.pay(request);
    }
}
