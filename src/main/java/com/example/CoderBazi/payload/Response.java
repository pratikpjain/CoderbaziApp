package com.example.CoderBazi.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Response {
    private int status;
    private String message;
    private Object result;
}
