package com.barberia.app.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Customer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "phone_number", length = 100)
    private String phoneNumber;
    @Column(name = "password", length = 255)
    private String password;
    @Column(name = "nick_name", length = 100)
    private String nickName;
    @Column(name = "email", length = 255)
    private String email;
    @Column(name = "is_membership")
    private boolean isMembership;
    @Column(name = "membership_name", length = 100)
    private String membershipName;

    @OneToMany(mappedBy = "customer")
    private List<Booking> bookings;

    public Customer() {
    }

    public Customer(Long id, String phoneNumber, String password, String nickName, String email, boolean isMembership, String membershipName, List<Booking> bookings) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.nickName = nickName;
        this.email = email;
        this.isMembership = isMembership;
        this.membershipName = membershipName;
        this.bookings = bookings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isMembership() {
        return isMembership;
    }

    public void setMembership(boolean membership) {
        isMembership = membership;
    }

    public String getMembershipName() {
        return membershipName;
    }

    public void setMembershipName(String membershipName) {
        this.membershipName = membershipName;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
