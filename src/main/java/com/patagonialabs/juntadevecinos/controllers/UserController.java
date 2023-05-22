package com.patagonialabs.juntadevecinos.controllers;

import com.patagonialabs.juntadevecinos.exceptions.ResourceNotFoundException;
import com.patagonialabs.juntadevecinos.models.User;
import com.patagonialabs.juntadevecinos.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class UserController {
    @Autowired
    private UserRepository repository;

    @GetMapping("/users")
    public List<User> listAllUsers(){
        return repository.findAll();
    }

    @PostMapping("/users")
    public User saveUser(@RequestBody User user){
        return repository.save(user);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no existe"));

        return ResponseEntity.ok(user);
    }
}
