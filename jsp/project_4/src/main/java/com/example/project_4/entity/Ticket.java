package com.example.project_4.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;

@Entity
@Table(name = "Tickets")
public class Ticket {
    
    @Id
    @Column(name = "ticket_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ticketId;

    @Column(name = "event_name", length = 255)
    private String eventName;

    @Column(name = "costumer_name", length = 255)
    private String customerName;

    @Column(name = "seat_number", length = 50)
    private String seatNumber;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "price")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal price;

    public int getTicketId() {
        return ticketId;
    }

    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSeatNumber() {
        return seatNumber;
    }
    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
