package com.mango.bookunittesting.ch7_2_1;

import java.util.Objects;

public class User {

    private int userId;
    private String email;
    private UserType type;

    public User(int userId, String email, UserType type) {
        this.userId = userId;
        this.email = email;
        this.type = type;
    }

    public void changeEmail(int userId, String newEmail) {
        Object[] data = Database.getUserById(userId);
        this.userId = userId;
        this.email = (String) data[1];
        this.type = (UserType) data[2];

        if (Objects.equals(email, newEmail)) {
            return;
        }

        Object[] companyData = Database.getCompany();
        String companyDomainName = (String) companyData[0];
        int numberOfEmployees = (int) companyData[1];

        String emailDomain = newEmail.split("@")[1];
        boolean isEmailCorporate = Objects.equals(emailDomain, companyDomainName);
        UserType newType = isEmailCorporate ? UserType.Employee : UserType.Customer;

        if (type != newType) {
            int delta = newType == UserType.Employee ? 1 : -1;
            int newNumber = numberOfEmployees + delta;
            Database.saveCompany(newNumber);
        }

        this.email = newEmail;
        this.type = newType;

        Database.saveUser(this);
        MessageBus.sendEmailChangedMessage(userId, newEmail);
    }

    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public UserType getType() {
        return type;
    }
}
