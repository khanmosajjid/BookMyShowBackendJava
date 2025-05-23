package com.mosajjid.bookmyshow.models;

import com.mosajjid.bookmyshow.models.enums.SeatStatus;
import com.mosajjid.bookmyshow.models.enums.ShowSeatStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeat extends BaseModel{
    @ManyToOne
    private  Show show;

    @ManyToOne
    private Seat seat;
    private Long price;
    private ShowSeatStatus showSeatStatus;
}
