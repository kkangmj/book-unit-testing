package com.mango.bookunittesting.ch8_6_2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DomainLogger implements IDomainLogger {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void userTypeHasChanged(int userId, UserType oldType, UserType newType) {
        logger.info(String.format("User %d changed type from %s to %s", userId, oldType, newType));
    }
}
