package com.mango.bookunittesting.ch7_2_4;


public class Database {

    private static final String companyDomainName = "mango.dev";
    private static int numberOfEmployees = 0;

    public Object[] getCompany() {
        return new Object[]{companyDomainName, numberOfEmployees};
    }

    public void saveCompany(int newNumber) {
        numberOfEmployees = newNumber;
    }
}
