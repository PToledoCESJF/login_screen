package com.loginscreen.controller;

import com.loginscreen.model.dto.UserSearchDTO;
import com.loginscreen.model.dto.UserSaveDTO;
import com.loginscreen.model.entity.User;
import com.loginscreen.model.mapper.UserMapper;
import com.loginscreen.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody UserSaveDTO userSaveDTO){
        User userSave = userService.insert(userSaveDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(userSave.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserSearchDTO> findById(@PathVariable Long id){
        UserSearchDTO userSearchDTO = userService.findById(id);
        return ResponseEntity.ok().body(userSearchDTO);
    }

}
