package com.mango.bookunittesting.ch8_6_2.v2;

public interface DomainEvent {

    boolean sendToMessageBus();
    boolean sendToDomainLogger();

}
