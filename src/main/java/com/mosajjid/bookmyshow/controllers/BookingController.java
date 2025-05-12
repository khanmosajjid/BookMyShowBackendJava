package com.mosajjid.bookmyshow.controllers;


import com.mosajjid.bookmyshow.dtos.BookingRequestDTO;
import com.mosajjid.bookmyshow.dtos.BookingResponseDTO;
import com.mosajjid.bookmyshow.dtos.ResponseStatus;
import com.mosajjid.bookmyshow.models.Booking;
import com.mosajjid.bookmyshow.services.BookingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    @PostMapping("/bookTicket")
    public BookingResponseDTO bookTicket(@RequestBody BookingRequestDTO bookingRequestDTO) {

        BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();
        try{
            Booking booking=this.bookingService.createBooking( bookingRequestDTO.getUserId(),
                    bookingRequestDTO.getShowId()
                    ,bookingRequestDTO.getShowSeatIds());
            bookingResponseDTO.setTicket(booking);
            bookingResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);

        } catch (Exception e) {
            bookingResponseDTO.setResponseStatus(ResponseStatus.FAILURE);
            throw new RuntimeException(e);
        }
        return bookingResponseDTO;

    }





}
