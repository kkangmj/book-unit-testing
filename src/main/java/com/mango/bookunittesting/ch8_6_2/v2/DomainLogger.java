package com.mango.bookunittesting.ch8_6_2.v2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DomainLogger implements IDomainLogger {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void userTypeHasChanged(int userId, UserType oldType, UserType newType) {
        logger.info("User {} changed type from {} to {}", userId, oldType, newType);
    }
}
