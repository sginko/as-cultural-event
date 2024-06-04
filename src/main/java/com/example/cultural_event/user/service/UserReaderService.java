package com.example.cultural_event.user.service;

import com.example.cultural_event.user.entity.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserReaderService {

    List<UserEntity> findByCity(String city);

    Optional<UserEntity> findByTechnicalId(UUID userId);
}