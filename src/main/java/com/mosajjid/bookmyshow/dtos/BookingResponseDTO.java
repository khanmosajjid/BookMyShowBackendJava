package com.mosajjid.bookmyshow.dtos;

import com.mosajjid.bookmyshow.models.Booking;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BookingResponseDTO {
    private Booking ticket;
    private ResponseStatus responseStatus;
}
