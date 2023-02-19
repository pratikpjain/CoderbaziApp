package com.example.CoderBazi.controller;

import com.example.CoderBazi.payload.Response;
import com.example.CoderBazi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "api/")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("addUser/")
    public ResponseEntity<Response> addUser(@RequestParam(value="name") String name,
                                            @RequestParam(value="userName") String userName,
                                            @RequestParam(value="phoneNumber") String phoneNumber) {
        return new ResponseEntity<>(userService.addUser(name, userName, phoneNumber), HttpStatus.CREATED);
    }
}
