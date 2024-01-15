package com.mango.bookunittesting.ch8_5.v1;

public class CheckOutService {
    
    public void checkOut(int orderId) {
        ReportGenerationService service = new ReportGenerationService();
        service.generateReport(orderId, this);
    }

    public void saveReport(Object o) {
    }
}

