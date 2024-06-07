package com.example.cultural_event.user.repository;

import com.example.cultural_event.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserReaderRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findByCity(String city);
}
