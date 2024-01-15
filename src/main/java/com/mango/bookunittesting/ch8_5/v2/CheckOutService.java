package com.mango.bookunittesting.ch8_5.v2;

public class CheckOutService {

    public void checkOut(int orderId) {
        ReportGenerationService service = new ReportGenerationService();
        Object report = service.generateReport(orderId, this);
    }
}