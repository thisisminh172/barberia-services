package com.barberia.app.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "employee")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String phoneNumber;
    private String password;
    private String firstName;
    private String lastName;
    private String role;
    private boolean gender;


//    private byte[] thumbnail;
    private boolean isActive;
    private String email;
    private String homeAddress;
    private Date dateOfBirth;
    private boolean isOnlineBookingAvailable;
    @ManyToOne
    @JoinColumn(name = "salon_id")
    private Salon salon;

    @OneToMany(mappedBy = "employee")
    private List<Booking> bookings;

    public Employee() {
    }
}
