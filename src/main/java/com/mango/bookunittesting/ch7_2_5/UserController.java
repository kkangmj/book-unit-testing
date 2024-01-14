package com.mango.bookunittesting.ch7_2_5;


import org.springframework.stereotype.Service;

@Service
public class UserController {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository = new CompanyRepository();
    private final MessageBus messageBus = new MessageBus();

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void changeEmail(int userId, String newEmail) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Company company = companyRepository.getCompany();

        user.changeEmail(newEmail, company);

        companyRepository.saveCompany(company);
        userRepository.save(user);
        messageBus.sendEmailChangedMessage(userId, newEmail);
    }
}
