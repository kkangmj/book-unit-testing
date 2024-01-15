package com.mango.bookunittesting.ch8_6_1;

import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "tb_user8_6_1")
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

    @Transient
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public User() {
    }

    public User(String email, UserType type) {
        this.email = email;
        this.type = type;
    }

    public String canChangeEmail() {
        return isEmailConfirmed ? "Can't change a confirmed email" : null;
    }

public void changeEmail(String newEmail, Company company) {
    logger.info(String.format("Changing email for user %s to %s", userId, newEmail));

    assert canChangeEmail() == null;

    if (Objects.equals(email, newEmail)) {
        return;
    }

    UserType newType = company.isEmailCorporate(newEmail) ? UserType.Employee : UserType.Customer;

    if (type != newType) {
        int delta = newType == UserType.Employee ? 1 : -1;
        company.changeNumberOfEmployees(delta);
        logger.info(String.format("User %d changed type from %s to %s", userId, type, newType));
    }

    this.email = newEmail;
    this.type = newType;
    emailChangedEvents.add(new EmailChangedEvent(userId, newEmail));

    logger.info(String.format("Email is changed for user %s", userId));
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
