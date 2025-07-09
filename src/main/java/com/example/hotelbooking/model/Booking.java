package com.example.hotelbooking.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    private LocalDate bookingDate;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;


    public Long getId() {
        return id; }

    public void setId(Long id) {
        this.id = id; }

    public String getCustomerName() {
        return customerName; }

    public void setCustomerName(String customerName) {
        this.customerName = customerName; }

    public LocalDate getBookingDate() {
        return bookingDate; }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate; }

    public Hotel getHotel() {
        return hotel; }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel; }
}

