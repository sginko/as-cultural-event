package com.example.cultural_event.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    AccountEntity findByTechnicalId(UUID technivalId);
}
