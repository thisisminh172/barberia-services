package com.barberia.app.dto;



public class PaymentReportDto {
    private long id;
    private String employeeName;
    private String datetime;
    private String paymentMethod;
    private double totalPrice;


    public PaymentReportDto() {
    }

    public PaymentReportDto(long id, String employeeName, String datetime, String paymentMethod, double totalPrice) {
        this.id = id;
        this.employeeName = employeeName;
        this.datetime = datetime;
        this.paymentMethod = paymentMethod;
        this.totalPrice = totalPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
