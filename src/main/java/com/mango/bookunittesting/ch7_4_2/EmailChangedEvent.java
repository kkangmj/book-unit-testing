package com.mango.bookunittesting.ch7_4_2;

import java.util.Objects;

public class EmailChangedEvent {

    public int userId;
    public String newEmail;

    public EmailChangedEvent(int userId, String newEmail) {
        this.userId = userId;
        this.newEmail = newEmail;
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
