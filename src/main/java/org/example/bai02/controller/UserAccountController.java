package org.example.bai02.controller;

import lombok.RequiredArgsConstructor;
import org.example.bai02.model.dto.request.UserAccountDTO;
import org.example.bai02.model.entity.UserAccount;
import org.example.bai02.service.UserAccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class UserAccountController {

    private final UserAccountService accountService;

    @PostMapping
    public UserAccount create(@RequestBody UserAccountDTO request) {

        return accountService.create(request);
    }
}
