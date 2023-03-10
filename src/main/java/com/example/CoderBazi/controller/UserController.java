package com.example.CoderBazi.controller;

import com.example.CoderBazi.entities.User;
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
    public ResponseEntity<Response> addUser(@RequestBody User user) {
        Response response = userService.addUser(user);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping("getUsers/")
    public ResponseEntity<Response> getUsers() {
        Response response = userService.getUsers();
        return new ResponseEntity<>(response, response.getStatus());
    }

    @DeleteMapping("deleteUser/")
    public ResponseEntity<Response> deleteUser(@RequestParam(value = "user_name") String user_name) {
        Response response = userService.DeleteUser(user_name);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
