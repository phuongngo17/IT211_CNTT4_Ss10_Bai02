package org.example.bai02.service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bai02.model.dto.request.PaymentDTO;
import org.example.bai02.model.entity.Order;
import org.example.bai02.model.entity.UserAccount;
import org.example.bai02.repository.OrderRepository;
import org.example.bai02.repository.UserAccountRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final OrderRepository orderRepository;
    private final UserAccountRepository accountRepository;

    @Transactional
    public String pay(PaymentDTO request) {

        try {

            Order order = orderRepository.findById(request.getOrderId())
                    .orElseThrow(() -> {
                        log.warn("Không tìm thấy order id={}", request.getOrderId());
                        return new RuntimeException("Order không tồn tại");
                    });

            if (!order.getUser().getId().equals(request.getUserId())) {

                log.warn("User {} không có quyền thanh toán order {}",
                        request.getUserId(),
                        request.getOrderId());

                throw new RuntimeException("Không đúng chủ sở hữu order");
            }

            if ("PAID".equals(order.getStatus())) {

                log.warn("Order {} đã được thanh toán", order.getId());

                throw new RuntimeException("Order đã PAID");
            }

            UserAccount account = accountRepository.findByUserId(request.getUserId())
                    .orElseThrow(() -> {
                        log.warn("Không tìm thấy account của user={}",
                                request.getUserId());

                        return new RuntimeException("Account không tồn tại");
                    });

            if (account.getBalance() < request.getAmount()) {

                log.warn("User {} không đủ số dư",
                        request.getUserId());

                throw new RuntimeException("Không đủ tiền");
            }

            if (request.getAmount() == 9999) {

                throw new RuntimeException("Đứt kết nối DB");
            }

            account.setBalance(
                    account.getBalance() - request.getAmount()
            );

            order.setStatus("PAID");

            accountRepository.save(account);
            orderRepository.save(order);

            log.info("Thanh toán thành công order={} user={}",
                    order.getId(),
                    request.getUserId());

            return "Thanh toán thành công";

        } catch (Exception e) {

            log.error("Lỗi hệ thống khi thanh toán", e);

            throw e;
        }
    }
}
