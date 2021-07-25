package com.barberia.app.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "salon")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Salon {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "salon_name", length = 200)
    private String salonName;
    @Column(name = "address", length = 200)
    private String address;
    @Column(name = "email", length = 200)
    private String email;
    @Column(name = "thumbnail_url", length = 200)
    private String thumbnailUrl;
    @Column(name = "phone_number", length = 100)
    private String phoneNumber;
    @Column(name = "opening_hour")
    private Time openingHour;
    @Column(name = "minute_in_one_time_slot")
    private int minuteInOneTimeSlot;
    @Column(name = "is_available_for_online_booking")
    private boolean isAvailableForOnlineBooking;
    @Column(name = "number_of_turn_in_one_time_slot")
    private int numberOfTurnInOneTimeSlot;
    @Column(name = "is_opened")
    private boolean isOpened;
    @Column(name = "is_car_parking_available")
    private boolean isCarParkingAvailable;
    @OneToMany(mappedBy = "salon")
    private List<Employee> employees;

    @OneToMany(mappedBy = "salon")
    private List<Booking> bookings;

    public Salon() {
    }

    public Salon(Long id, String salonName, String address, String email,String thumbnailUrl, String phoneNumber, Time openingHour, int minuteInOneTimeSlot, boolean isAvailableForOnlineBooking, int numberOfTurnInOneTimeSlot, boolean isOpened, boolean isCarParkingAvailable, List<Employee> employees, List<Booking> bookings) {
        this.id = id;
        this.salonName = salonName;
        this.address = address;
        this.email = email;
        this.thumbnailUrl = thumbnailUrl;
        this.phoneNumber = phoneNumber;
        this.openingHour = openingHour;
        this.minuteInOneTimeSlot = minuteInOneTimeSlot;
        this.isAvailableForOnlineBooking = isAvailableForOnlineBooking;
        this.numberOfTurnInOneTimeSlot = numberOfTurnInOneTimeSlot;
        this.isOpened = isOpened;
        this.isCarParkingAvailable = isCarParkingAvailable;
        this.employees = employees;
        this.bookings = bookings;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Time getOpeningHour() {
        return openingHour;
    }

    public void setOpeningHour(Time openingHour) {
        this.openingHour = openingHour;
    }

    public int getMinuteInOneTimeSlot() {
        return minuteInOneTimeSlot;
    }

    public void setMinuteInOneTimeSlot(int minuteInOneTimeSlot) {
        this.minuteInOneTimeSlot = minuteInOneTimeSlot;
    }

    public boolean isAvailableForOnlineBooking() {
        return isAvailableForOnlineBooking;
    }

    public void setAvailableForOnlineBooking(boolean availableForOnlineBooking) {
        isAvailableForOnlineBooking = availableForOnlineBooking;
    }

    public int getNumberOfTurnInOneTimeSlot() {
        return numberOfTurnInOneTimeSlot;
    }

    public void setNumberOfTurnInOneTimeSlot(int numberOfTurnInOneTimeSlot) {
        this.numberOfTurnInOneTimeSlot = numberOfTurnInOneTimeSlot;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public boolean isCarParkingAvailable() {
        return isCarParkingAvailable;
    }

    public void setCarParkingAvailable(boolean carParkingAvailable) {
        isCarParkingAvailable = carParkingAvailable;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
