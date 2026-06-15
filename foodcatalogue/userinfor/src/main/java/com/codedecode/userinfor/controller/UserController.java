package com.codedecode.userinfor.controller;

import com.codedecode.userinfor.dto.UserDTO;
import com.codedecode.userinfor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @GetMapping("/fetchUserById/{id}")
    public ResponseEntity<UserDTO> fetchUserById(@PathVariable Integer id) {
        // Implement the logic to fetch user by ID using the userService
        // For example, you can create a method in UserService to fetch user by ID and return the appropriate ResponseEntity
        return userService.fetchUserById(id);
    }
}
