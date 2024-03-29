package com.example.CoderBazi.services.impl;

import com.example.CoderBazi.entities.User;
import com.example.CoderBazi.payload.Response;
import com.example.CoderBazi.payload.UsersDto;
import com.example.CoderBazi.repositories.UsersRepository;
import com.example.CoderBazi.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public Response addUser(User user) {
        try {
            String ValidationResponse = ValidateUser(user.getName(), user.getUserName(), user.getPhoneNumber());
            if (ValidationResponse != null) {
                return new Response(HttpStatus.BAD_REQUEST, ValidationResponse, null);
            }
            if (UserWithUserNameExists(user.getUserName())) {
                return new Response(HttpStatus.BAD_REQUEST, "User with this user-name already exists! Please try another user-name.", null);
            }
            user.setCreatedAt(new Date());
            user.setUpdatedAt(new Date());
            usersRepository.save(user);
            return new Response(HttpStatus.CREATED, "New User Created", user);
        } catch (Exception e) {
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
    }

    @Override
    public Response getUsers() {
        try {
            List<User> userList = usersRepository.getUsers();
            int numberOfUsers = userList.size();
            return new Response(HttpStatus.OK, "There are total " + numberOfUsers + " users in our database", userList);
        } catch (Exception e) {
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
    }

    @Override
    public Response DeleteUser(String userName) {
        try {
            User deletedUser = usersRepository.getUserByUserName(userName);
            usersRepository.deleteAllByUserName(userName);
            return new Response(HttpStatus.OK, "Users Deleted successfully", deletedUser);
        } catch (Exception e) {
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR,  e.getMessage(), null);
        }
    }
    String ValidateUser(String name, String userName, String phoneNumber) {

        String validateName = ValidateName(name);
        if (validateName != null) return validateName;
        String validateUserName = ValidateUserName(userName);
        if (validateUserName != null) return validateUserName;
        String validatePhoneNumber = ValidatePhoneNumber(phoneNumber);
        if (validatePhoneNumber != null) return validatePhoneNumber;

        return null;
    }

    String ValidateName(String name) {
        if(name == null) return "Name field cannot be empty";
        if(name.length() >= 100) return "Name must not exceed 100 characters";
        return null;
    }
    String ValidateUserName(String userName) {
        if(userName == null) return "user name field can't be empty";
        if(userName.length() < 3 || userName.length() > 20) return "user-name length must be between 3 to 20.";
        for(int i = 0 ; i < userName.length() ; ++i) {
            char c = userName.charAt(i);
            if(!(isAlphabet(c) || isNumber(c) || isSpecial(c))) return "user-name must contain alphabets, number, '.' and '_' only. No other characters are allowed!";
        }
        return null;
    }
    String ValidatePhoneNumber(String phoneNumber) {
        if(phoneNumber == null) return "phone number field can't be empty";
        if(phoneNumber.length() != 10) return "phone-number must be of length 10";
        for(int i = 0 ; i < 10 ; ++i) {
            char c = phoneNumber.charAt(i);
            if(i == 0 && c == '0') return "First digit of number should not be zero";
            if(!isNumber(c)) return "Phone number should contain only numerical values";
        }
        return null;
    }
    boolean isAlphabet(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
    boolean isNumber(char c) {
        return (c >= '0' && c <= '9');
    }
    boolean isSpecial(char c) {
        return c == '.' || c == '_';
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
