package com.example.hotelbooking.controller;

import com.example.hotelbooking.model.Booking;
import com.example.hotelbooking.model.Hotel;
import com.example.hotelbooking.repository.BookingRepository;
import com.example.hotelbooking.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @PostMapping("/book")
    public String bookHotel(@RequestParam Long hotelId, @RequestParam String customerName) {
        Optional<Hotel> hotelOpt = hotelRepository.findById(hotelId);
        if (hotelOpt.isEmpty()) {
            return "Hotel not found!";
        }

        Booking booking = new Booking();
        booking.setCustomerName(customerName);
        booking.setHotel(hotelOpt.get());
        booking.setBookingDate(java.time.LocalDate.now());

        bookingRepository.save(booking);
        return "Booking successful!";
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @DeleteMapping("/cancel/{id}")
    public String cancelBooking(@PathVariable Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return "Booking cancelled successfully!";
        } else {
            return "Booking not found!";
        }
    }
}
