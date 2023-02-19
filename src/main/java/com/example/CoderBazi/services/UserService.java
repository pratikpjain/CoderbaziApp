package com.example.CoderBazi.services;
import com.example.CoderBazi.payload.Response;
import org.springframework.stereotype.Service;

import java.util.Date;


public interface UserService {
    Response addUser(String name, String userName, String phoneNumber);
    Response getUsers();
    Response DeleteUser(String userName);
}
