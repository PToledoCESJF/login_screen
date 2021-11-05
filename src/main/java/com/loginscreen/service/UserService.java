package com.loginscreen.service;

import com.loginscreen.controller.model.entity.User;
import com.loginscreen.repository.UserRepository;
import com.loginscreen.service.exception.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private UserRepository userRepository;

    public User insert(User user){
        user.setId(null);
        user = userRepository.save(user);
        return user;
    }

    public User findById(Long id){
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado. Id: " + id + ", Tipo: " + User.class.getName()
        ));
    }
}
