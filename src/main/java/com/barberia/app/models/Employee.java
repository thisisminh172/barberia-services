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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nick_name",length = 100)
    private String nickName;
    @Column(name = "phone_number", length = 100)
    private String phoneNumber;
    @Column(name = "password", length = 255)
    private String password;
    @Column(name = "first_name", length = 100)
    private String firstName;
    @Column(name = "last_name", length = 100)
    private String lastName;
    @Column(name = "role", length = 100)
    private String role;
    @Column(name = "gender")
    private boolean gender;
    @Column(name = "thumbnail_url", length = 200)
    private String thumbnailUrl;
    @Column(name = "is_active")
    private boolean isActive;
    @Column(name = "email", length = 255)
    private String email;
    @Column(name = "home_address", length = 200)
    private String homeAddress;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column(name = "is_online_booking_available")
    private boolean isOnlineBookingAvailable;
    @ManyToOne
    @JoinColumn(name = "salon_id", referencedColumnName = "id")
    private Salon salon;

    @OneToMany(mappedBy = "employee")
    private List<Booking> bookings;

    public Employee() {
    }

    public Employee(Long id, String nickName, String phoneNumber, String password, String firstName, String lastName, String role, boolean gender, boolean isActive,String thumbnailUrl, String email, String homeAddress, Date dateOfBirth, boolean isOnlineBookingAvailable, Salon salon, List<Booking> bookings) {
        this.id = id;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.gender = gender;
        this.isActive = isActive;
        this.thumbnailUrl = thumbnailUrl;
        this.email = email;
        this.homeAddress = homeAddress;
        this.dateOfBirth = dateOfBirth;
        this.isOnlineBookingAvailable = isOnlineBookingAvailable;
        this.salon = salon;
        this.bookings = bookings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isOnlineBookingAvailable() {
        return isOnlineBookingAvailable;
    }

    public void setOnlineBookingAvailable(boolean onlineBookingAvailable) {
        isOnlineBookingAvailable = onlineBookingAvailable;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
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
