package com.mosajjid.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDTO {
    private String name;
    private String email;
    private String password;
}
