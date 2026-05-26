package org.example.bai02.service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bai02.model.dto.request.OrderDTO;
import org.example.bai02.model.entity.Order;
import org.example.bai02.model.entity.User;
import org.example.bai02.repository.OrderRepository;
import org.example.bai02.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public Order create(OrderDTO request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> {
                    log.warn("Không tìm thấy user id={}", request.getUserId());
                    return new RuntimeException("User không tồn tại");
                });

        Order order = Order.builder()
                .user(user)
                .totalAmount(request.getTotalAmount())
                .status("PENDING")
                .build();

        log.info("Tạo order thành công cho user={}", user.getUsername());

        return orderRepository.save(order);
    }
}