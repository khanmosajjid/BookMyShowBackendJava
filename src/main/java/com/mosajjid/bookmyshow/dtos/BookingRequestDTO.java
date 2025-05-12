package com.mosajjid.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookingRequestDTO {
    private Long userId;
    private List<Long> showSeatIds;
    private Long showId;
}
