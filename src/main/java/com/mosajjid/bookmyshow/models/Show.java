package com.mosajjid.bookmyshow.models;

import com.mosajjid.bookmyshow.models.enums.Feature;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "shows")
public class Show extends  BaseModel{
    @ManyToOne
    private Movie movie;

    @ManyToOne
    private Screen screen;
    private Long startTime;
    private Long endTime;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> feature;

}
