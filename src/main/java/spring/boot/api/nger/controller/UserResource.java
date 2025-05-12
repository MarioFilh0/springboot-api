package spring.boot.api.nger.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.boot.api.nger.model.Department;
import spring.boot.api.nger.model.User;
import spring.boot.api.nger.services.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    UserService userService;

    @PostMapping(value = "/add")
    public ResponseEntity<User> create(@RequestBody User user){
        userService.create(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok().body(users);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<User> deleteById(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User user = userService.getById(id);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<User> updateById(@PathVariable Long id, User newUser) {
        userService.update(id, newUser);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }
}
