package com.example.cultural_event.user.repository;

import com.example.cultural_event.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByTechnicalId(UUID technicalId);
}
