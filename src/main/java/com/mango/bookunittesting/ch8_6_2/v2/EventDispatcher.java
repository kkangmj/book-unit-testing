package com.mango.bookunittesting.ch8_6_2.v2;

import org.aspectj.bridge.IMessage;

import java.util.List;

public class EventDispatcher implements IEventDispatcher {

    private final IMessageBus messageBus;
    private final IDomainLogger domainLogger;

    public EventDispatcher(IMessageBus messageBus, IDomainLogger domainLogger) {
        this.messageBus = messageBus;
        this.domainLogger = domainLogger;
    }

    @Override
    public void dispatch(List<DomainEvent> events) {
        events.forEach(event ->
        {
            event.sendToMessageBus();
            event.sendToDomainLogger();
        });
    }
}
