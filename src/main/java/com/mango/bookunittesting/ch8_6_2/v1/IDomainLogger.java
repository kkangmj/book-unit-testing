package com.mango.bookunittesting.ch8_6_2.v1;

public interface IDomainLogger {

    void userTypeHasChanged(int userId, UserType oldType, UserType newType);
}
