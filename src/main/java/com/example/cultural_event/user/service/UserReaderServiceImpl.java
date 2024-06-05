package com.example.cultural_event.user.service;

import com.example.cultural_event.user.entity.UserEntity;
import com.example.cultural_event.user.repository.UserReaderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserReaderServiceImpl implements UserReaderService {
    private final UserReaderRepository userReaderRepository;

    public UserReaderServiceImpl(UserReaderRepository userReaderRepository) {
        this.userReaderRepository = userReaderRepository;
    }

    @Override
    public List<UserEntity> findByCity(String city) {
        return userReaderRepository.findByCity(city);
    }

    @Override
    public Optional<UserEntity> findByTechnicalId(UUID technicalId) {
        return userReaderRepository.findByTechnicalId(technicalId);
    }
}