package com.mango.bookunittesting.ch8_6_2.v2;

import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "tb_user8_6_2_v2")
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
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    @Transient
    private final Logger logger = LoggerFactory.getLogger("ROOT");

    @Transient
    private final IDomainLogger domainLogger = new DomainLogger();

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
        logger.info("Changing email for user {} to {}", userId, newEmail);

        assert canChangeEmail() == null;

        if (Objects.equals(email, newEmail)) {
            return;
        }

        UserType newType = company.isEmailCorporate(newEmail) ? UserType.Employee : UserType.Customer;

        if (type != newType) {
            int delta = newType == UserType.Employee ? 1 : -1;
            company.changeNumberOfEmployees(delta);
            domainEvents.add(new UserTypeChangedEvent(userId, type, newType));
        }

        this.email = newEmail;
        this.type = newType;
        domainEvents.add(new EmailChangedEvent(userId, newEmail));

        logger.info("Email is changed for user {}", userId);
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

    public List<DomainEvent> getDomainEvents() {
        return domainEvents;
    }
}
