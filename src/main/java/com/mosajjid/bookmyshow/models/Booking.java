package com.mosajjid.bookmyshow.models;

import com.mosajjid.bookmyshow.models.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
public class Booking extends  BaseModel{
    private String bookingNumber;
    private BookingStatus bookingStatus;

    @ManyToOne
    private  User user;

    @ManyToMany
    private  List<ShowSeat> showSeat;

    @OneToMany
    private List<Payment> payments;
    private Long amount;


}

//Cardinality
//Booking ShowSeat
// 1       M
// M         1

//Booking Payment
// 1       M
// 1        1