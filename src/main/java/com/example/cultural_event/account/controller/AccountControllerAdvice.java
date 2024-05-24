package com.example.cultural_event.account.controller;

import com.example.cultural_event.account.AccountException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AccountControllerAdvice {
    @ExceptionHandler({AccountException.class})
    public ResponseEntity handleAccountServiceException(AccountException e) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(e.getMessage());
    }
}
