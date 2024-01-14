package com.mango.bookunittesting.ch7_4_2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    void change_email_from_corporate_to_non_corporate() {
        Company company = new Company("mango.org", 1);
        User sut = new User(1, "user@mango.org", UserType.Employee, false);

        sut.changeEmail("new@gmail.com", company);

        assertThat(company.getNumberOfEmployees()).isEqualTo(0);
        assertThat(sut.getEmail()).isEqualTo("new@gmail.com");
        assertThat(sut.getType()).isEqualTo(UserType.Customer);
        assertThat(sut.getEmailChangedEvents()).hasSize(1);
        assertThat(sut.getEmailChangedEvents()).contains(new EmailChangedEvent(1, "new@gmail.com"));
    }
}