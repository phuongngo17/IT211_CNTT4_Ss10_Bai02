package org.example.bai02.model.dto.request;

import lombok.Data;

@Data
public class PaymentDTO{

    private Long orderId;

    private Long userId;

    private Double amount;
}