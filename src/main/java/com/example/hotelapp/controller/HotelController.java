package com.example.hotelapp.controller;

import com.example.hotelapp.model.Hotel;
import com.example.hotelapp.model.GuestDetails;
import com.example.hotelapp.model.Reservation;
import com.example.hotelapp.model.User;
import com.example.hotelapp.repository.HotelRepository;
import com.example.hotelapp.repository.GuestDetailsRepository;
import com.example.hotelapp.repository.ReservationRepository;
import com.example.hotelapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import org.springframework.http.ResponseEntity;
import com.example.hotelapp.DTO.ReservationRequest;
import com.example.hotelapp.DTO.GuestDetailsRequest;



@RestController
@RequestMapping("/api")
public class HotelController {

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    GuestDetailsRepository guestDetailsRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/hotels")
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @GetMapping("/guestDetails")
    public List<GuestDetails> getAllGuestDetails() {
        return guestDetailsRepository.findAll();
    }

    @GetMapping("/reservation")
    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }

    @GetMapping("/user")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @PostMapping("/addHotels")
    public Hotel createHotel(@Valid @RequestBody Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @PostMapping("/createReservations")
    public ResponseEntity<?> createReservationWithGuests(@Valid @RequestBody ReservationRequest reservationRequest) {
        try {
            // Assuming the Reservation entity uses Integer for IDs
            Reservation reservation = new Reservation();
            reservation.setHotel(hotelRepository.findById(reservationRequest.getHotelId()).orElseThrow(() -> new RuntimeException("Hotel not found")));
            reservation.setUser(userRepository.findById(reservationRequest.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));
            reservation.setCheck_in_date(reservationRequest.getCheckInDate());
            reservation.setCheck_out_date(reservationRequest.getCheckOutDate());
            Reservation savedReservation = reservationRepository.save(reservation);
            reservationRequest.getGuests().forEach(guestDto -> {
                GuestDetails guest = new GuestDetails();
                guest.setName(guestDto.getName());
                guest.setGender(guestDto.getGender().toUpperCase());
                guest.setReservation(savedReservation);
                guestDetailsRepository.save(guest);
            });
            return ResponseEntity.ok("Reservation and guest details saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating reservation: " + e.getMessage());
        }
    }
}
