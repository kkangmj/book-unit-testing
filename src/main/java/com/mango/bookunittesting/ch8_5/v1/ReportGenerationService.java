package com.mango.bookunittesting.ch8_5.v1;

public class ReportGenerationService {
    public void generateReport(int orderId, CheckOutService checkOutService) {
        // report 생성

        // checkOutService 호출해 저장
        checkOutService.saveReport(null);
    }
}
