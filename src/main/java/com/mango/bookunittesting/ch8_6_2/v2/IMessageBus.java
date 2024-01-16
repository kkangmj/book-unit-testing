package com.mango.bookunittesting.ch8_6_2.v2;

public interface IMessageBus {

    void sendEmailChangedMessage(int userId, String newEmail);
}
