package com.example.cultural_event.account.repository;

import com.example.cultural_event.account.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountReaderRepository extends JpaRepository<AccountEntity, Long> {
    List<AccountEntity> findByCity(String city);

    Optional<AccountEntity> findByTechnicalId(UUID accountId);
}