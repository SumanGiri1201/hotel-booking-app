package com.example.hotelbooking.controller;

import com.example.hotelbooking.model.Booking;
import com.example.hotelbooking.model.Room;
import com.example.hotelbooking.repository.BookingRepository;
import com.example.hotelbooking.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private RoomRepository roomRepo;

    @PostMapping("/book/{roomId}")
    public String bookRoom(@PathVariable Long roomId, @RequestParam String guestName) {
        Room room = roomRepo.findById(roomId).orElseThrow();

        if (room.isBooked()) {
            return "Room already booked.";
        }

        room.setBooked(true);
        roomRepo.save(room);

        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setGuestName(guestName);
        bookingRepo.save(booking);

        return "Room " + room.getRoomNumber() + " booked by " + guestName;
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    @DeleteMapping("/cancel/{bookingId}")
    public String cancelBooking(@PathVariable Long bookingId) {
        Booking booking = bookingRepo.findById(bookingId).orElseThrow();
        Room room = booking.getRoom();
        room.setBooked(false);
        roomRepo.save(room);
        bookingRepo.delete(booking);
        return "Booking cancelled.";
    }
}
