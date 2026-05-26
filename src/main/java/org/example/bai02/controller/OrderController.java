package org.example.bai02.controller;

import lombok.RequiredArgsConstructor;
import org.example.bai02.model.dto.request.OrderDTO;
import org.example.bai02.model.entity.Order;
import org.example.bai02.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order create(@RequestBody OrderDTO request) {

        return orderService.create(request);
    }
}