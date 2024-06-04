package com.example.cultural_event.user.service;

import com.example.cultural_event.user.entity.UserEntity;
import com.example.cultural_event.user.repository.UserReaderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}