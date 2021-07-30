package com.barberia.app.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ServiceDto {

    private Long id;
    private String serviceName;
    private double originalPrice;
    private double discountPrice;
    private String isDiscount;
    private int timeConsume;
    private String description;

    public ServiceDto() {
    }

    public ServiceDto(Long id, String serviceName, double originalPrice, double discountPrice, String isDiscount, int timeConsume, String description) {
        this.id = id;
        this.serviceName = serviceName;
        this.originalPrice = originalPrice;
        this.discountPrice = discountPrice;
        this.isDiscount = isDiscount;
        this.timeConsume = timeConsume;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getIsDiscount() {
        return isDiscount;
    }

    public void setIsDiscount(String isDiscount) {
        this.isDiscount = isDiscount;
    }

    public int getTimeConsume() {
        return timeConsume;
    }

    public void setTimeConsume(int timeConsume) {
        this.timeConsume = timeConsume;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
