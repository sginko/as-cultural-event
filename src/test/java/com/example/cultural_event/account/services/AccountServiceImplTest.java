package com.example.cultural_event.account.services;

import com.example.cultural_event.account.AccountEntity;
import com.example.cultural_event.account.AccountRepository;
import com.example.cultural_event.account.AccountRequestDto;
import com.example.cultural_event.event.model.dto.EventRequestDto;
import com.example.cultural_event.event.model.enity.EventEntity;
import com.example.cultural_event.event.model.service.EventService;
import com.example.cultural_event.notification.NotificationEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AccountServiceImplTest {
    private final String CORRECT_NAME = "Flo69";
    private final String CORRECT_EMAIL = "Destin.Smith@yahoo.com";
    private final String CITY = "South Luthermouth";
    @Autowired
    private AccountService accountService;
    @Autowired
    private EventService eventService;
    @Autowired
    private AccountRepository accountRepository;

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
    }

    @Test
    public void should_create_new_account_when_data_are_correct() {
        //given
        AccountRequestDto accountRequestDto = prepareAccountRequestDto(CORRECT_NAME, CITY, CORRECT_EMAIL);

        //when
        accountService.addNewAccount(accountRequestDto);
        List<AccountEntity> all = accountRepository.findAll();
        //then
        assertThat(all.size()).isEqualTo(1);
    }
    @Test
    public void should_find_all_notifications_for_account_by_id() {
        //given
        AccountRequestDto accountRequestDto = new AccountRequestDto(CORRECT_NAME, CITY, CORRECT_EMAIL);

        accountService.addNewAccount(accountRequestDto);
        List<AccountEntity> all = accountRepository.findAll();
        UUID technicalId = all.get(0).getTechnicalId();
        EventRequestDto event = new EventRequestDto("event", CITY, LocalDateTime.now());
        eventService.addEvent(event);
        //when
        List<NotificationEntity> allNotifications = accountService.findAllNotifications(technicalId);
        //then
        assertThat(allNotifications.size()).isEqualTo(1);
    }
    private AccountRequestDto prepareAccountRequestDto(String name, String city, String email) {
        return new AccountRequestDto(name, city, email);
    }

}