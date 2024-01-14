package com.mango.bookunittesting.ch7_2_5;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    public void change_email_from_non_corporate_to_corporate() {
        Company company = new Company("mango.org", 1);
        User sut = new User(1, "user@gmail.com", UserType.Customer);

        sut.changeEmail("new@mango.org", company);

        assertThat(company.getNumberOfEmployees()).isEqualTo(2);
        assertThat(sut.getEmail()).isEqualTo("new@mango.org");
        assertThat(sut.getType()).isEqualTo(UserType.Employee);
    }
}