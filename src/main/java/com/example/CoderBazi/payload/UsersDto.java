package com.example.CoderBazi.payload;

import lombok.Data;

import java.util.Date;

@Data
public class UsersDto {
    private String name;
    private String userName;
    private String phoneNumber;
    private Date createdAt;
    private Date updatedAt;
}
