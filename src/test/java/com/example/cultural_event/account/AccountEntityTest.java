package com.example.cultural_event.account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountEntityTest {
    private final String CORRECT_NAME = "Flo69";
    private final String CORRECT_EMAIL = "Destin.Smith@yahoo.com";
    private final String EMAIL_WITH_SPACE = "Destin Smith@yahoo.com";
    private final String EMAIL_WITHOUT_AT = "Destin.Smithyahoo.com";
    private final String CITY = "South Luthermouth";

    @Test
    public void should_create_account_with_correct_fields() {
        //given
        //when
        AccountEntity accountEntity = new AccountEntity(CORRECT_NAME, CITY, CORRECT_EMAIL);
        //then
        assertThat(accountEntity).isNotNull();
    }

    @Test
    public void should_create_account_with_empty_name() {
        //given
        //when
        Executable e = () -> new AccountEntity("", CITY, CORRECT_EMAIL);
        //then
        AccountException exc = assertThrows(AccountException.class, e);
        assertThat(exc.getMessage().contains("empty")).isTrue();
    }
    @Test
    public void should_create_account_with_wrong_email() {
        //given
        //when
        Executable e = () -> new AccountEntity(CORRECT_NAME, CITY, EMAIL_WITH_SPACE);
        //then
        AccountException exc = assertThrows(AccountException.class, e);
        assertThat(exc.getMessage().contains("Check")).isTrue();
    }
    @Test
    public void should_create_account_without_at() {
        //given
        //when
        Executable e = () -> new AccountEntity(CORRECT_NAME, CITY, EMAIL_WITHOUT_AT);
        //then
        AccountException exc = assertThrows(AccountException.class, e);
        assertThat(exc.getMessage().contains("Check")).isTrue();
    }
}