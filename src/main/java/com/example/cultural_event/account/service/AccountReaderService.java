package com.example.cultural_event.account.service;

import com.example.cultural_event.account.entity.AccountEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountReaderService {
    List<AccountEntity> findByCity(String city);

    Optional<AccountEntity> findByTechnicalId(UUID accountId);
}