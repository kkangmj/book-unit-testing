package com.mango.bookunittesting.ch8_6_2.v2;


public class CompanyRepository {

    private static final String companyDomainName = "mango.dev";
    private static int numberOfEmployees = 0;

    public Company getCompany() {
        return new Company(companyDomainName, numberOfEmployees);
    }

    public synchronized void saveCompany(Company company) {
        numberOfEmployees = company.getNumberOfEmployees();
    }
}
