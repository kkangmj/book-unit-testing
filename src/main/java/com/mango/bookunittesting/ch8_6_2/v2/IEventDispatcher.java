package com.mango.bookunittesting.ch8_6_2.v2;

import java.util.List;

public interface IEventDispatcher {

    public void dispatch(List<DomainEvent> events);
}
