package com.barberia.app.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;
import java.time.LocalTime;

public class SalonUpdateDto {
    private Long id;
    private String salonName;
    private String address;
    private String email;
    private String thumbnailUrl;
    private String phoneNumber;
    private LocalTime openedHour;
    private LocalTime closedHour;
    private int minuteInOneTimeSlot;
    private String isAvailableForOnlineBooking;
    private int numberOfTurnInOneTimeSlot;
    private String isOpened;
    private String isCarParkingAvailable;

    public SalonUpdateDto() {
    }

    public SalonUpdateDto(Long id, String salonName, String address, String email, String thumbnailUrl, String phoneNumber, LocalTime openedHour, LocalTime closedHour, int minuteInOneTimeSlot, String isAvailableForOnlineBooking, int numberOfTurnInOneTimeSlot, String isOpened, String isCarParkingAvailable) {
        this.id = id;
        this.salonName = salonName;
        this.address = address;
        this.email = email;
        this.thumbnailUrl = thumbnailUrl;
        this.phoneNumber = phoneNumber;
        this.openedHour = openedHour;
        this.closedHour = closedHour;
        this.minuteInOneTimeSlot = minuteInOneTimeSlot;
        this.isAvailableForOnlineBooking = isAvailableForOnlineBooking;
        this.numberOfTurnInOneTimeSlot = numberOfTurnInOneTimeSlot;
        this.isOpened = isOpened;
        this.isCarParkingAvailable = isCarParkingAvailable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSalonName() {
        return salonName;
    }

    public void setSalonName(String salonName) {
        this.salonName = salonName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalTime getOpenedHour() {
        return openedHour;
    }

    public void setOpenedHour(LocalTime openedHour) {
        this.openedHour = openedHour;
    }

    public LocalTime getClosedHour() {
        return closedHour;
    }

    public void setClosedHour(LocalTime closedHour) {
        this.closedHour = closedHour;
    }

    public int getMinuteInOneTimeSlot() {
        return minuteInOneTimeSlot;
    }

    public void setMinuteInOneTimeSlot(int minuteInOneTimeSlot) {
        this.minuteInOneTimeSlot = minuteInOneTimeSlot;
    }

    public String getIsAvailableForOnlineBooking() {
        return isAvailableForOnlineBooking;
    }

    public void setIsAvailableForOnlineBooking(String isAvailableForOnlineBooking) {
        this.isAvailableForOnlineBooking = isAvailableForOnlineBooking;
    }

    public int getNumberOfTurnInOneTimeSlot() {
        return numberOfTurnInOneTimeSlot;
    }

    public void setNumberOfTurnInOneTimeSlot(int numberOfTurnInOneTimeSlot) {
        this.numberOfTurnInOneTimeSlot = numberOfTurnInOneTimeSlot;
    }

    public String getIsOpened() {
        return isOpened;
    }

    public void setIsOpened(String isOpened) {
        this.isOpened = isOpened;
    }

    public String getIsCarParkingAvailable() {
        return isCarParkingAvailable;
    }

    public void setIsCarParkingAvailable(String isCarParkingAvailable) {
        this.isCarParkingAvailable = isCarParkingAvailable;
    }
}
