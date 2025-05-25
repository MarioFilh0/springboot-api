package spring.boot.api.nger.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import spring.boot.api.nger.dto.UserDTO;
import spring.boot.api.nger.mapper.UserMapper;
import spring.boot.api.nger.model.User;
import spring.boot.api.nger.repository.UserRepository;
import spring.boot.api.nger.services.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    // Em UserResource.java
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName(); // O login do usuário autenticado

        User user = (User) userRepository.findByLogin(login);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(userMapper.toDto(user)); // Retorna o UserDTO do usuário logado
    }

    @PostMapping(value = "/add")
    public ResponseEntity<User> create(@RequestBody @Valid User user){
        userService.create(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<User> deleteById(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        UserDTO user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<UserDTO> updateById(@PathVariable Long id, @RequestBody @Valid UserDTO dto) {
        UserDTO newDto = userService.update(id, dto);
        return new ResponseEntity<>(newDto, HttpStatus.OK);
    }
}
