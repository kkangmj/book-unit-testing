package com.mango.bookunittesting.ch7_2_4;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column
    private String email;

    @Column
    private UserType type;

    public User() {
    }

    public User(int userId, String email, UserType type) {
        this.userId = userId;
        this.email = email;
        this.type = type;
    }

    public int changeEmail(String newEmail, String companyDomainName, int numberOfEmployees) {
        if (Objects.equals(email, newEmail)) {
            return numberOfEmployees;
        }

        String emailDomain = newEmail.split("@")[1];
        boolean isEmailCorporate = Objects.equals(emailDomain, companyDomainName);
        UserType newType = isEmailCorporate ? UserType.Employee : UserType.Customer;

        if (type != newType) {
            int delta = newType == UserType.Employee ? 1 : -1;
            int newNumber = numberOfEmployees + delta;
            numberOfEmployees = newNumber;
        }

        this.email = newEmail;
        this.type = newType;

        return numberOfEmployees;
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
