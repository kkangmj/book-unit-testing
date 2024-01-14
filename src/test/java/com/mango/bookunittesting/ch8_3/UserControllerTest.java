package com.mango.bookunittesting.ch8_3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserControllerTest {

    @Autowired
    @Qualifier("userRepository8_3")
    UserRepository userRepository;

    @Test
    void change_email_from_corporate_to_non_corporate() {
        // given
        User user = userRepository.save(new User("user@mango.dev", UserType.Employee));
        CompanyRepository companyRepository = new CompanyRepository();
        companyRepository.saveCompany(new Company("mango.dev", 1));

        MessageBus messageBusMock = mock(MessageBus.class);
        UserController sut = new UserController(userRepository, messageBusMock);

        // when
        String result = sut.changeEmail(user.getUserId(), "new@gmail.com");

        // then
        assertThat(result).isEqualTo("OK");

        // 사용자 상태 검증
        User userFromDb = userRepository.findById(user.getUserId()).orElseThrow();
        assertThat(userFromDb.getEmail()).isEqualTo("new@gmail.com");
        assertThat(userFromDb.getType()).isEqualTo(UserType.Customer);

        // 회사 상태 검증
        Company companyFromDb = companyRepository.getCompany();
        assertThat(companyFromDb.getNumberOfEmployees()).isEqualTo(0);

        // 목 상호 작용 확인
        verify(
            messageBusMock,
            times(1)
        ).sendEmailChangedMessage(user.getUserId(), "new@gmail.com");
    }

}