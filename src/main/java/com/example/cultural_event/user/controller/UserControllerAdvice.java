package com.example.cultural_event.user.controller;

import com.example.cultural_event.user.service.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler({UserException.class})
    public ResponseEntity handleUserServiceException(UserException e) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(e.getMessage());
    }
}
