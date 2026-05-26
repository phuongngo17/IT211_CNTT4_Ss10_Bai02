package org.example.bai02.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bai02.model.dto.request.UserAccountDTO;
import org.example.bai02.model.entity.User;
import org.example.bai02.model.entity.UserAccount;
import org.example.bai02.repository.UserAccountRepository;
import org.example.bai02.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAccountService {

    private final UserRepository userRepository;
    private final UserAccountRepository accountRepository;

    public UserAccount create(UserAccountDTO request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> {
                    log.warn("Không tìm thấy user id={}", request.getUserId());
                    return new RuntimeException("User không tồn tại");
                });

        UserAccount account = UserAccount.builder()
                .user(user)
                .balance(request.getBalance())
                .build();

        log.info("Tạo tài khoản thành công cho user={}", user.getUsername());

        return accountRepository.save(account);
    }
}