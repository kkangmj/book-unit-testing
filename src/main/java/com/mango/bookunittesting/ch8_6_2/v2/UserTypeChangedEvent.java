package com.mango.bookunittesting.ch8_6_2.v2;

public class UserTypeChangedEvent implements DomainEvent {

    private final int userId;
    private final UserType oldType;
    private final UserType newType;

    public UserTypeChangedEvent(int userId, UserType oldType, UserType newType) {
        this.userId = userId;
        this.oldType = oldType;
        this.newType = newType;
    }

    @Override
    public boolean sendToMessageBus() {
        return false;
    }

    @Override
    public boolean sendToDomainLogger() {
        return true;
    }

    public int getUserId() {
        return userId;
    }

    public UserType getOldType() {
        return oldType;
    }

    public UserType getNewType() {
        return newType;
    }
}
