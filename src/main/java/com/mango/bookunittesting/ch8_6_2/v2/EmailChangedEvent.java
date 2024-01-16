package com.mango.bookunittesting.ch8_6_2.v2;

import java.util.Objects;

public class EmailChangedEvent implements DomainEvent {

    private final int userId;
    private final String newEmail;

    public EmailChangedEvent(int userId, String newEmail) {
        this.userId = userId;
        this.newEmail = newEmail;
    }

    @Override
    public boolean sendToMessageBus() {
        return true;
    }

    @Override
    public boolean sendToDomainLogger() {
        return false;
    }

    public int getUserId() {
        return userId;
    }

    public String getNewEmail() {
        return newEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailChangedEvent that = (EmailChangedEvent) o;
        return userId == that.userId && Objects.equals(newEmail, that.newEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, newEmail);
    }
}
