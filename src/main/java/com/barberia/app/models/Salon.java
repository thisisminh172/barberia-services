package com.barberia.app.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;
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
    @Column(name = "opened_hour")
    private LocalTime openedHour;
    @Column(name = "closed_hour")
    private LocalTime closedHour;
    @Column(name = "minute_in_one_time_slot")
    private int minuteInOneTimeSlot;
    @Column(name = "available_for_online_booking")
    private boolean availableForOnlineBooking;
    @Column(name = "number_of_turn_in_one_time_slot")
    private int numberOfTurnInOneTimeSlot;
    @Column(name = "opened")
    private boolean opened;
    @Column(name = "car_parking_available")
    private boolean carParkingAvailable;
    @OneToMany(mappedBy = "salon")
    private List<Employee> employees;

    @OneToMany(mappedBy = "salon")
    private List<Booking> bookings;

    public Salon() {
    }

    public Salon(Long id, String salonName, String address, String email,String thumbnailUrl, String phoneNumber, LocalTime openedHour, LocalTime closedHour, int minuteInOneTimeSlot, boolean availableForOnlineBooking, int numberOfTurnInOneTimeSlot, boolean opened, boolean carParkingAvailable, List<Employee> employees, List<Booking> bookings) {
        this.id = id;
        this.salonName = salonName;
        this.address = address;
        this.email = email;
        this.thumbnailUrl = thumbnailUrl;
        this.phoneNumber = phoneNumber;
        this.openedHour = openedHour;
        this.closedHour = closedHour;
        this.minuteInOneTimeSlot = minuteInOneTimeSlot;
        this.availableForOnlineBooking = availableForOnlineBooking;
        this.numberOfTurnInOneTimeSlot = numberOfTurnInOneTimeSlot;
        this.opened = opened;
        this.carParkingAvailable = carParkingAvailable;
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

    public boolean isAvailableForOnlineBooking() {
        return availableForOnlineBooking;
    }

    public void setAvailableForOnlineBooking(boolean availableForOnlineBooking) {
        this.availableForOnlineBooking = availableForOnlineBooking;
    }

    public int getNumberOfTurnInOneTimeSlot() {
        return numberOfTurnInOneTimeSlot;
    }

    public void setNumberOfTurnInOneTimeSlot(int numberOfTurnInOneTimeSlot) {
        this.numberOfTurnInOneTimeSlot = numberOfTurnInOneTimeSlot;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public boolean isCarParkingAvailable() {
        return carParkingAvailable;
    }

    public void setCarParkingAvailable(boolean carParkingAvailable) {
        this.carParkingAvailable = carParkingAvailable;
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
