package com.mango.bookunittesting.ch7_4_1;


import org.springframework.stereotype.Service;

@Service("userController7_4_1")
public class UserController {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository = new CompanyRepository();
    private final MessageBus messageBus = new MessageBus();

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        messageBus.sendEmailChangedMessage(userId, newEmail);

        return "OK";
    }
}
