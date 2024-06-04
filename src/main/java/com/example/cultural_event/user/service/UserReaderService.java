package com.example.cultural_event.user.service;

import com.example.cultural_event.user.entity.UserEntity;

import java.util.List;

public interface UserReaderService {

    List<UserEntity> findByCity(String city);
}