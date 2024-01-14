package com.mango.bookunittesting.ch7_4_2;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "tb_user7_4_2")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column
    private String email;

    @Column
    private UserType type;

    @Column
    private boolean isEmailConfirmed;

    @Transient
    private final List<EmailChangedEvent> emailChangedEvents = new ArrayList<>();

    public User() {
    }

    public User(int userId, String email, UserType type, boolean isEmailConfirmed) {
        this.userId = userId;
        this.email = email;
        this.type = type;
        this.isEmailConfirmed = isEmailConfirmed;
    }

    public String canChangeEmail() {
        return isEmailConfirmed ? "Can't change a confirmed email" : null;
    }

    public void changeEmail(String newEmail, Company company) {
        assert canChangeEmail() == null;

        if (Objects.equals(email, newEmail)) {
            return;
        }

        UserType newType = company.isEmailCorporate(newEmail) ? UserType.Employee : UserType.Customer;

        if (type != newType) {
            int delta = newType == UserType.Employee ? 1 : -1;
            company.changeNumberOfEmployees(delta);
        }

        this.email = newEmail;
        this.type = newType;
        emailChangedEvents.add(new EmailChangedEvent(userId, newEmail));
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

    public List<EmailChangedEvent> getEmailChangedEvents() {
        return emailChangedEvents;
    }
}
