package com.example.hotelbooking.controller;

import com.example.hotelbooking.model.Hotel;
import com.example.hotelbooking.model.Room;
import com.example.hotelbooking.repository.HotelRepository;
import com.example.hotelbooking.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelRepository hotelRepo;

    @Autowired
    private RoomRepository roomRepo;


    @PostMapping("/add")
    public String addHotel(@RequestBody Hotel hotel) {
        hotelRepo.save(hotel);
        return "Hotel added successfully!";
    }


    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelRepo.findAll();
    }

    @PostMapping("/{hotelId}/rooms/add")
    public String addRoomToHotel(@PathVariable Long hotelId, @RequestBody Room room) {
        Hotel hotel = hotelRepo.findById(hotelId).orElseThrow();
        room.setHotel(hotel);
        roomRepo.save(room);


        List<Room> rooms = hotel.getRooms();
        rooms.add(room);
        hotel.setRooms(rooms);
        hotelRepo.save(hotel);

        return "Room added to hotel!";
    }
}
