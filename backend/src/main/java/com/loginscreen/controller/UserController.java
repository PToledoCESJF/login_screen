package com.loginscreen.controller;

import com.loginscreen.model.dto.UserSearchDTO;
import com.loginscreen.model.dto.UserSaveDTO;
import com.loginscreen.model.dto.UserUpdateDTO;
import com.loginscreen.model.entity.User;
import com.loginscreen.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody UserUpdateDTO userUpdateDTO, @PathVariable Long id){
        userService.update(userUpdateDTO, id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<UserSearchDTO>> findAll(Pageable pageable){
        return ResponseEntity.ok().body(userService.findAll(pageable));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
