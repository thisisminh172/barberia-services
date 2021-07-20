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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String salonName;
    private String address;
    private String email;
//    private byte[] thumbnail;
    private String phoneNumber;
    private Time openingHour;
    private int minuteInOneTimeSlot;
    private boolean isAvailableForOnlineBooking;
    private int numberOfTurnInOneTimeSlot;
    private boolean isOpened;
    private boolean isCarParkingAvailable;
    @OneToMany(mappedBy = "salon")
    private List<Employee> employees;

    @OneToMany(mappedBy = "salon")
    private List<Booking> bookings;

    public Salon() {
    }
}
