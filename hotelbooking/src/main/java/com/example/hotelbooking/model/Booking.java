package com.example.hotelbooking.model;

import jakarta.persistence.*;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String guestName;

    @OneToOne
    private Room room;

    public Long getId() { return id; }
    public String getGuestName() { return guestName; }
    public Room getRoom() { return room; }

    public void setId(Long id) { this.id = id; }
    public void setGuestName(String guestName) { this.guestName = guestName; }
    public void setRoom(Room room) { this.room = room; }
}
