package com.barberia.app.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "booking")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Booking {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "chosen_time_slot")
    private LocalDateTime chosenTimeSlot;
    //Customer can provide number of person come to shop
    @Column(name = "description", length = 299)
    private String description;
    @Column(name = "online_booking")
    private boolean onlineBooking;
    @Column(name="status", length = 50)
    private String status;
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "salon_id", referencedColumnName = "id")
    private Salon salon;

    @OneToMany(mappedBy = "booking")
    private List<BookingDetail> bookingDetails;

    @OneToOne(mappedBy = "booking")
    private Turn turn;

    public Booking() {
    }

    public Booking(Long id, LocalDateTime chosenTimeSlot, String description, boolean onlineBooking, String status, Employee employee, Customer customer, Salon salon, List<BookingDetail> bookingDetails, Turn turn) {
        this.id = id;
        this.chosenTimeSlot = chosenTimeSlot;
        this.description = description;
        this.onlineBooking = onlineBooking;
        this.status = status;
        this.employee = employee;
        this.customer = customer;
        this.salon = salon;
        this.bookingDetails = bookingDetails;
        this.turn = turn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getChosenTimeSlot() {
        return chosenTimeSlot;
    }

    public void setChosenTimeSlot(LocalDateTime chosenTimeSlot) {
        this.chosenTimeSlot = chosenTimeSlot;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isOnlineBooking() {
        return onlineBooking;
    }

    public void setOnlineBooking(boolean onlineBooking) {
        this.onlineBooking = onlineBooking;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public List<BookingDetail> getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(List<BookingDetail> bookingDetails) {
        this.bookingDetails = bookingDetails;
    }

    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }
}
