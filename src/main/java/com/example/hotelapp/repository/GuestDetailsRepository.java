package com.example.hotelapp.repository;

import com.example.hotelapp.model.GuestDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GuestDetailsRepository extends JpaRepository<GuestDetails, Long> {

}
