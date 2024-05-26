package com.example.cultural_event.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountReader extends JpaRepository<AccountEntity,Long> {
    List<AccountEntity> findByCity(String city);
}
