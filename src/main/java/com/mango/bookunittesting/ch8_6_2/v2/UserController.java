package com.mango.bookunittesting.ch8_6_2.v2;

import org.springframework.stereotype.Service;

@Service("userController8_6_2_v2")
public class UserController {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository = new CompanyRepository();
    private final EventDispatcher eventDispatcher = new EventDispatcher(new MessageBus(), new DomainLogger());

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
        eventDispatcher.dispatch(user.getDomainEvents());

        return "OK";
    }
}
