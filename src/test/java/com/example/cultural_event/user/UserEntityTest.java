package com.example.cultural_event.user;

import com.example.cultural_event.user.entity.UserEntity;
import com.example.cultural_event.user.service.UserException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserEntityTest {
    private final String CORRECT_NAME = "Flo69";
    private final String CORRECT_EMAIL = "Destin.Smith@yahoo.com";
    private final String EMAIL_WITH_SPACE = "Destin Smith@yahoo.com";
    private final String EMAIL_WITHOUT_AT = "Destin.Smithyahoo.com";
    private final String CITY = "South Luthermouth";

    @Test
    public void should_create_account_with_correct_fields() {
        //given

        //when
        UserEntity userEntity = new UserEntity(CORRECT_NAME, CITY, CORRECT_EMAIL);

        //then
        assertThat(userEntity).isNotNull();
    }

    @Test
    public void should_not_create_account_with_empty_name() {
        //given

        //when
        Executable e = () -> new UserEntity("", CITY, CORRECT_EMAIL);

        //then
        UserException exc = assertThrows(UserException.class, e);
        assertThat(exc.getMessage().contains("empty")).isTrue();
    }

    @Test
    public void should_not_create_account_with_wrong_email() {
        //given

        //when
        Executable e = () -> new UserEntity(CORRECT_NAME, CITY, EMAIL_WITH_SPACE);

        //then
        UserException exc = assertThrows(UserException.class, e);
        assertThat(exc.getMessage().contains("Check")).isTrue();
    }

    @Test
    public void should_not_create_account_without_at() {
        //given

        //when
        Executable e = () -> new UserEntity(CORRECT_NAME, CITY, EMAIL_WITHOUT_AT);

        //then
        UserException exc = assertThrows(UserException.class, e);
        assertThat(exc.getMessage().contains("Check")).isTrue();
    }
}