package com.example.CoderBazi.services;
import com.example.CoderBazi.entities.User;
import com.example.CoderBazi.payload.Response;
import org.springframework.stereotype.Service;

import java.util.Date;


public interface UserService {
    Response addUser(User user);
    Response getUsers();
    Response DeleteUser(String userName);
}
