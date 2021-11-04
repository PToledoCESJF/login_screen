package com.loginscreen.service;

import com.loginscreen.controller.model.entity.User;
import com.loginscreen.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private UserRepository userRepository;

    public User insert(User user){
        user.setId(null);
        user = userRepository.save(user);
        return user;
    }

}
