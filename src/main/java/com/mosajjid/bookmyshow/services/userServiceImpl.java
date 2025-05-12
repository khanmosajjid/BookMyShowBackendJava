package com.mosajjid.bookmyshow.services;

import com.mosajjid.bookmyshow.models.User;
import com.mosajjid.bookmyshow.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Service
public class userServiceImpl implements UserService {
    UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    userServiceImpl(UserRepository userRepository,BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Override

    public User createUser(String name, String email, String password){
        User user = new User();



       Optional<User> optionalUser= userRepository.findByEmail(email);
        if(optionalUser.isPresent()){
            throw new RuntimeException("Email already exists");
        }
        user.setEmail(email);
        user.setUsername(name);

        user.setPassword(this.bCryptPasswordEncoder.encode(password));
        return userRepository.save(user);
    }
}

//COnfiguration class
