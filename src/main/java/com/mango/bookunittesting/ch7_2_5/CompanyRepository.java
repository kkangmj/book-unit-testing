package com.mango.bookunittesting.ch7_2_5;


public class CompanyRepository {

    private static final String companyDomainName = "mango.dev";
    private static int numberOfEmployees = 0;

    public Company getCompany() {
        return new Company(companyDomainName, numberOfEmployees);
    }

    public void saveCompany(Company company) {
        numberOfEmployees = company.getNumberOfEmployees();
    }
}
