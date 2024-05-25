package com.example.cultural_event.account.services;

import com.example.cultural_event.account.AccountEntity;
import com.example.cultural_event.account.AccountRepository;
import com.example.cultural_event.account.AccountRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AccountServiceImplTest {
    private final String CORRECT_NAME = "Flo69";
    private final String CORRECT_EMAIL = "Destin.Smith@yahoo.com";
    private final String CITY = "South Luthermouth";
    @Autowired
    private AccountService accountService;
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

    private AccountRequestDto prepareAccountRequestDto(String name, String city, String email) {
        return new AccountRequestDto(name, city, email);
    }
}