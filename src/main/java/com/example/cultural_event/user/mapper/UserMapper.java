package com.example.cultural_event.user.mapper;

import com.example.cultural_event.user.dto.UserRequestDto;
import com.example.cultural_event.user.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(UserRequestDto userRequestDto) {
        return new UserEntity(userRequestDto.getName(), userRequestDto.getCity(), userRequestDto.getEmail());
    }
}
