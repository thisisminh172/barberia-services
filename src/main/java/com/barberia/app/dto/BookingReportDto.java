package com.barberia.app.dto;

public class BookingReportDto {
    private long id;
    private String customerName;
    private String time;
    private String phoneNumber;
    private String status;

    public BookingReportDto() {
    }

    public BookingReportDto(long id, String customerName, String time, String phoneNumber, String status) {
        this.id = id;
        this.customerName = customerName;
        this.time = time;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
