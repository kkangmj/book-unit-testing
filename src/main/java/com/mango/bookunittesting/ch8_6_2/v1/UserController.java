package com.mango.bookunittesting.ch8_6_2.v1;


import com.mango.bookunittesting.ch8_3.Company;
import com.mango.bookunittesting.ch8_3.User;
import com.mango.bookunittesting.ch8_3.*;
import org.springframework.stereotype.Service;

@Service("userController8_6_2_v1")
public class UserController {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository = new CompanyRepository();
    private final MessageBus messageBus;

    public UserController(UserRepository userRepository, MessageBus messageBus) {
        this.userRepository = userRepository;
        this.messageBus = messageBus;
    }

    public String changeEmail(int userId, String newEmail) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        String error = user.canChangeEmail();
        if (error != null) {
            return error;
        }

        Company company = companyRepository.getCompany();
        user.changeEmail(newEmail, company);

        companyRepository.saveCompany(company);
        userRepository.save(user);
        user.getEmailChangedEvents().forEach(e -> {
            messageBus.sendEmailChangedMessage(e.getUserId(), e.getNewEmail());
        });

        return "OK";
    }
}
