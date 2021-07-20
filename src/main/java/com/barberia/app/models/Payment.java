package com.barberia.app.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "payment")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Payment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "total_price")
    private double totalPrice;
    @Column(name = "payment_method", length = 100)
    private String paymentMethod;

    @OneToOne
    @JoinColumn(name = "turn_id", referencedColumnName = "id")
    private Turn turn;

    public Payment() {
    }

    public Payment(Long id, double totalPrice, String paymentMethod, Turn turn) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
        this.turn = turn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }
}
