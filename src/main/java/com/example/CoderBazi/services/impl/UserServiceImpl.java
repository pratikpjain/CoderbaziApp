package com.example.CoderBazi.services.impl;

import com.example.CoderBazi.entities.User;
import com.example.CoderBazi.payload.Response;
import com.example.CoderBazi.payload.UsersDto;
import com.example.CoderBazi.repositories.UsersRepository;
import com.example.CoderBazi.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    private ModelMapper mapper;
    private final UsersRepository usersRepository;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository, ModelMapper mapper) {
        this.usersRepository = usersRepository;
        this.mapper = mapper;
    }
    @Override
    public Response addUser(String name, String userName, String phoneNumber) {
        User user = new User();
        user.setName(name);
        user.setUserName(userName);
        user.setPhoneNumber(phoneNumber);
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        usersRepository.save(user);
        return new Response(200, "New User Created", null);
    }
    private UsersDto mapToDTO(User user){
        UsersDto usersDto = mapper.map(user, UsersDto.class);
        return usersDto;
    }

    private User mapToEntity(UsersDto usersDto){
        User user = mapper.map(usersDto, User.class);
        return user;
    }
}
