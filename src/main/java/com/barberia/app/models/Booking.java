package com.barberia.app.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.sql.Timestamp;
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
    private Timestamp  chosenTimeSlot;
    //Customer can provide number of person come to shop
    @Column(name = "description")
    private String description;

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

    public Booking(Long id, Timestamp chosenTimeSlot, String description, Employee employee, Customer customer, Salon salon, List<BookingDetail> bookingDetails, Turn turn) {
        this.id = id;
        this.chosenTimeSlot = chosenTimeSlot;
        this.description = description;
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

    public Timestamp getChosenTimeSlot() {
        return chosenTimeSlot;
    }

    public void setChosenTimeSlot(Timestamp chosenTimeSlot) {
        this.chosenTimeSlot = chosenTimeSlot;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
