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
import java.util.List;

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
        if(UserWithUserNameExists(userName)) {
            return new Response(401, "User with this user-name already exists! Please try another user-name.", null);
        }
        User user = new User();
        user.setName(name);
        user.setUserName(userName);
        user.setPhoneNumber(phoneNumber);
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        usersRepository.save(user);
        return new Response(200, "New User Created", null);
    }

    @Override
    public Response getUsers() {
        List<User> userList = usersRepository.getUsers();

        return new Response(200, "Here are the users", userList);
    }

    @Override
    public Response DeleteUser(String userName) {
        User deletedUser = usersRepository.getUserByUserName(userName);
        usersRepository.deleteAllByUserName(userName);
        return new Response(200, "Users Deleted successfully", deletedUser);
    }
    private boolean UserWithUserNameExists(String userName) {
        if(usersRepository.UserWithUserNameExists(userName) > 0) return true;
        return false;
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
