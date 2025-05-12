package com.mosajjid.bookmyshow.services;

import com.mosajjid.bookmyshow.models.User;

public interface UserService {

    public User createUser(String name, String email, String password);
}
