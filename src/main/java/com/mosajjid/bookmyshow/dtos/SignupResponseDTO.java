package com.mosajjid.bookmyshow.dtos;

import com.mosajjid.bookmyshow.models.User;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SignupResponseDTO {
    private ResponseStatus responseStatus;
    private User user;
}
