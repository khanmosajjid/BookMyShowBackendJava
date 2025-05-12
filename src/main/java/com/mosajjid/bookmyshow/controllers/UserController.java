package com.mosajjid.bookmyshow.controllers;

import com.mosajjid.bookmyshow.dtos.ResponseStatus;
import com.mosajjid.bookmyshow.dtos.SignupRequestDTO;
import com.mosajjid.bookmyshow.dtos.SignupResponseDTO;
import com.mosajjid.bookmyshow.models.User;
import com.mosajjid.bookmyshow.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
@PostMapping("/signup")
    public SignupResponseDTO signup(SignupRequestDTO signupRequestDTO) {

        SignupResponseDTO signupResponseDTO = new SignupResponseDTO();
        try{
            User user= this.userService.createUser(signupRequestDTO.getName(),
                    signupRequestDTO.getEmail(),
                    signupRequestDTO.getPassword());
            signupResponseDTO.setUser(user);
            signupResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);

        } catch (Exception e) {
            signupResponseDTO.setResponseStatus(ResponseStatus.FAILURE);
            throw new RuntimeException(e);
        }
        return signupResponseDTO;
    }


}
