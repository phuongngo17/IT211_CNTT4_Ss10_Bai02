package org.example.bai02.model.dto.request;

import lombok.Data;

@Data
public class OrderDTO {
    private Long userId;

    private Double totalAmount;
}
