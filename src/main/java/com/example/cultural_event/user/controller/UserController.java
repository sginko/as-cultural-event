package com.example.cultural_event.user.controller;

import com.example.cultural_event.user.dto.UserRequestDto;
import com.example.cultural_event.user.service.UserService;
import com.example.cultural_event.notification.model.dto.NotificationResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewAccount(@RequestBody UserRequestDto userRequestDto) {
        userService.addNewUser(userRequestDto);
    }

    @GetMapping("/{user_id}/notifications")
    public List<NotificationResponseDto> findAllNotificationsForSubscribedEvents(@PathVariable("user_id") UUID technicalId,
                                                                                 @RequestParam(defaultValue = "false") Boolean subscribed) {
        if (subscribed){
            return userService.findAllNotificationsForSubscribedEvents(technicalId);
        }
        return userService.findAllNotifications(technicalId);
    }
}
