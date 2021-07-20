package com.barberia.app.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "service")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serviceName;
    private double originalPrice;
    private double discountPrice;
    private boolean isDiscount;
    private int timeConsume;
    private String description;

    @OneToMany(mappedBy = "service")
    private List<BookingDetail> bookingDetails;

    public Service() {
    }
}
