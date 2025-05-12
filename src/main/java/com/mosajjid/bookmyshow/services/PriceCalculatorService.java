package com.mosajjid.bookmyshow.services;

import com.mosajjid.bookmyshow.models.ShowSeat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculatorService {
    public  Long calculcatePrice(List<ShowSeat> showSeats){

        Long amount = 0L;

        for(ShowSeat showSeat:  showSeats){
            amount += showSeat.getPrice();
        }

        return amount;
    }
}
