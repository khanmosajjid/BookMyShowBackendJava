package com.mosajjid.bookmyshow.services;

import com.mosajjid.bookmyshow.exception.SeatNoLongerAvailableException;
import com.mosajjid.bookmyshow.exception.ShowNotFoundException;
import com.mosajjid.bookmyshow.exception.ShowSeatNotValidException;
import com.mosajjid.bookmyshow.exception.UserNotFoundException;
import com.mosajjid.bookmyshow.models.Booking;
import com.mosajjid.bookmyshow.models.Show;
import com.mosajjid.bookmyshow.models.ShowSeat;
import com.mosajjid.bookmyshow.models.User;
import com.mosajjid.bookmyshow.models.enums.BookingStatus;
import com.mosajjid.bookmyshow.models.enums.ShowSeatStatus;
import com.mosajjid.bookmyshow.repositories.ShowRepository;
import com.mosajjid.bookmyshow.repositories.ShowSeatRepository;
import com.mosajjid.bookmyshow.repositories.UserRepository;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.Optional;
import java.util.concurrent.locks.Lock;

@Service
public class BookingServiceImp implements BookingService {

    UserRepository userRepository;
    ShowSeatRepository showSeatRepository;
    ShowRepository showRepository;
    PriceCalculatorService priceCalculatorService;

    private Lock lock;

    public BookingServiceImp(UserRepository userRepository,
                             ShowSeatRepository showSeatRepository,
                             ShowRepository showRepository,PriceCalculatorService priceCalculatorService) {
        this.userRepository = userRepository;
        this.showSeatRepository = showSeatRepository;
        this.showRepository = showRepository;
        this.priceCalculatorService = priceCalculatorService;

    }

    @Override
    public Booking createBooking(Long userId, Long showId, List<Long> showSeatIds)  {

        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty()) {
            throw new UserNotFoundException("User with id " + userId + " not found");

        }
        Optional<Show> show = showRepository.findById(showId);

        if(show.isEmpty()) {
            throw new ShowNotFoundException("Show with id " + showId + " not found");
        }
        List<ShowSeat> showSeats = showSeatRepository.findAllShowSeatAndLock(showSeatIds);

        if(showSeats.size() != showSeatIds.size()) {
            throw new ShowSeatNotValidException("All show seats are not valid");
        }
        for(ShowSeat showSeat : showSeats) {
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
                throw new SeatNoLongerAvailableException("Show seat are no longer available");
            }
        }



        Booking booking = new Booking();
         booking.setUser(user.get());
         booking.setBookingNumber("Booking_ " +userId +"Show_ "+showId);
         booking.setBookingStatus(BookingStatus.PENDING);


         for(ShowSeat showSeat : showSeats) {
             showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
         }

         showSeatRepository.saveAll(showSeats);

         booking.setShowSeat(showSeats);
         booking.setAmount(this.priceCalculatorService.calculcatePrice(showSeats));

         //if payment is cancelled rollback this

        booking.setBookingStatus(BookingStatus.CONFIRMED);

        for(ShowSeat showSeat : showSeats) {
            showSeat.setShowSeatStatus(ShowSeatStatus.BOOKED);
        }
        showSeatRepository.saveAll(showSeats);





        return booking;
    }
}
