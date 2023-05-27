package com.patagonialabs.juntadevecinos.controllers;

import com.patagonialabs.juntadevecinos.exceptions.ResourceNotFoundException;
import com.patagonialabs.juntadevecinos.models.User;
import com.patagonialabs.juntadevecinos.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userEdit){
        User user = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("El usuario no existe"));

        user.setFirstName(userEdit.getFirstName());
        user.setLastName(userEdit.getLastName());
        user.setDni(userEdit.getDni());
        user.setEmail(userEdit.getEmail());

        return ResponseEntity.ok(repository.save(user));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity <Map<String,Boolean>> deleteUser(@PathVariable Long id){
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("El usuario no existe"));

        repository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
