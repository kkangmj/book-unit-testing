package com.mango.bookunittesting.ch7_2_3;


import java.util.HashMap;
import java.util.Map;

public class Database {

    private static final Map<Integer, User> userMap = new HashMap<>();
    private static final String companyDomainName = "mango.dev";
    private static int numberOfEmployees = 0;

    public Object[] getUserById(int userId) {
        User user = userMap.get(userId);
        return new Object[]{user.getUserId(), user.getEmail(), user.getType()};
    }

    public Object[] getCompany() {
        return new Object[]{companyDomainName, numberOfEmployees};
    }

    public void saveCompany(int newNumber) {
        numberOfEmployees = newNumber;
    }

    public void saveUser(User user) {
        userMap.put(user.getUserId(), user);
    }
}
