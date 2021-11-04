package com.loginscreen.controller;

import com.loginscreen.controller.model.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @GetMapping
    public List<User> findAll(){

        User u1 = new User(1L, "User 01", "user01@mail.com");
        User u2 = new User(2L, "User 02", "user02@mail.com");
        User u3 = new User(3L, "User 03", "user03@mail.com");

        List<User> list = new ArrayList<>();
        list.add(u1);
        list.add(u2);
        list.add(u3);

        return list;
    }
}
