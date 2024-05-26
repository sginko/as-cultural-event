package com.example.cultural_event.notification;

import com.example.cultural_event.account.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface NotificationReader extends JpaRepository<NotificationEntity, Long> {
    @Transactional
    @Query("SELECT n FROM NotificationEntity n WHERE n.account = :account")
    List<NotificationEntity> getAllNotificationsForAccount(AccountEntity account);

}
