package com.mango.bookunittesting.ch7_2_4;

import org.springframework.stereotype.Service;

@Service("userController7_2_4")
public class UserController {

    private final UserRepository userRepository;
    private final Database database = new Database();
    private final MessageBus messageBus = new MessageBus();

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void changeEmail(int userId, String newEmail) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Object[] companyData = database.getCompany();
        String companyDomainName = (String) companyData[0];
        int numberOfEmployees = (int) companyData[1];

        int newNumberOfEmployees = user.changeEmail(
            newEmail, companyDomainName, numberOfEmployees
        );

        database.saveCompany(newNumberOfEmployees);
        userRepository.save(user);
        messageBus.sendEmailChangedMessage(userId, newEmail);
    }
}
