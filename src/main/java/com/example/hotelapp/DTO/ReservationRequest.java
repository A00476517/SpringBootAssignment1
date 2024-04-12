package com.example.hotelapp.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.example.hotelapp.DTO.GuestDetailsRequest;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import javax.validation.Valid;
import java.util.List;

public class ReservationRequest {
    private Integer hotelId;
    private Integer userId;
    private Date checkInDate;
    private Date checkOutDate;
    private List<GuestDetailsRequest> guests;

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public List<GuestDetailsRequest> getGuests() {
        return guests;
    }

    public void setGuests(List<GuestDetailsRequest> guests) {
        this.guests = guests;
    }

}
