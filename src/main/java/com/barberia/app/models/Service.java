package com.barberia.app.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "service")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Service {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "service_name", length = 100)
    private String serviceName;
    @Column(name = "original_price")
    private double originalPrice;
    @Column(name = "discount_price")
    private double discountPrice;
    @Column(name = "is_discount")
    private boolean isDiscount;
    @Column(name = "time_consume")
    private int timeConsume;
    @Column(name = "description", length = 300)
    private String description;

    @OneToMany(mappedBy = "service")
    private List<BookingDetail> bookingDetails;

    public Service() {
    }

    public Service(Long id, String serviceName, double originalPrice, double discountPrice, boolean isDiscount, int timeConsume, String description, List<BookingDetail> bookingDetails) {
        this.id = id;
        this.serviceName = serviceName;
        this.originalPrice = originalPrice;
        this.discountPrice = discountPrice;
        this.isDiscount = isDiscount;
        this.timeConsume = timeConsume;
        this.description = description;
        this.bookingDetails = bookingDetails;
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

    public boolean isDiscount() {
        return isDiscount;
    }

    public void setDiscount(boolean discount) {
        isDiscount = discount;
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

    public List<BookingDetail> getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(List<BookingDetail> bookingDetails) {
        this.bookingDetails = bookingDetails;
    }
}
