package com.barberia.app.dto;

import com.barberia.app.models.Salon;

import javax.persistence.*;
import java.sql.Date;

public class EmployeeDto {
    private Long id;
    private String nickName;
    private String phoneNumber;
    private String password;
    private String firstName;
    private String lastName;
    private String role;
    private String gender;
    private String thumbnailUrl;
    private String isActive;
    private String email;
    private String homeAddress;
    private Date dateOfBirth;
    private String isOnlineBookingAvailable;
    private Salon salon;

    public EmployeeDto() {
    }

    public EmployeeDto(Long id, String nickName, String phoneNumber, String password, String firstName, String lastName, String role, String gender, String thumbnailUrl, String isActive, String email, String homeAddress, Date dateOfBirth, String isOnlineBookingAvailable, Salon salon) {
        this.id = id;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.gender = gender;
        this.thumbnailUrl = thumbnailUrl;
        this.isActive = isActive;
        this.email = email;
        this.homeAddress = homeAddress;
        this.dateOfBirth = dateOfBirth;
        this.isOnlineBookingAvailable = isOnlineBookingAvailable;
        this.salon = salon;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
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

    public String getIsOnlineBookingAvailable() {
        return isOnlineBookingAvailable;
    }

    public void setIsOnlineBookingAvailable(String isOnlineBookingAvailable) {
        this.isOnlineBookingAvailable = isOnlineBookingAvailable;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }
}
