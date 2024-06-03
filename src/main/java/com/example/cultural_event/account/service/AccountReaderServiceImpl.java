package com.example.cultural_event.account.service;

import com.example.cultural_event.account.entity.AccountEntity;
import com.example.cultural_event.account.repository.AccountReaderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountReaderServiceImpl implements AccountReaderService{
    private final AccountReaderRepository accountReaderRepository;

    public AccountReaderServiceImpl(AccountReaderRepository accountReaderRepository) {
        this.accountReaderRepository = accountReaderRepository;
    }

    @Override
    public List<AccountEntity> findByCity(String city) {
        return accountReaderRepository.findByCity(city);
    }

    @Override
    public Optional<AccountEntity> findByTechnicalId(UUID accountId) {
        return accountReaderRepository.findByTechnicalId(accountId);
    }
}