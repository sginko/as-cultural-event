package com.example.cultural_event.account.controller;

import com.example.cultural_event.account.dto.AccountRequestDto;
import com.example.cultural_event.account.service.AccountService;
import com.example.cultural_event.notification.enity.NotificationEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")

public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewAccount(@RequestBody AccountRequestDto accountRequestDto) {
        accountService.addNewAccount(accountRequestDto);
    }

    @GetMapping("/{user_id}/notifications")
    public List<NotificationEntity> findAllNotificationsForAccount(@PathVariable("user_id") UUID technicalId) {
        return accountService.findAllNotifications(technicalId);
    }

}
