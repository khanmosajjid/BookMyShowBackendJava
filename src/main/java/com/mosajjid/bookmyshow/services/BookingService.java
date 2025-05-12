package com.mosajjid.bookmyshow.services;

import com.mosajjid.bookmyshow.models.Booking;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

import java.util.*;

@Service
public interface BookingService {
    @Transactional(isolation= Isolation.SERIALIZABLE,rollbackFor=Exception.class)
    public Booking createBooking(Long userId, Long showId, List<Long> showSeatIds);

}
