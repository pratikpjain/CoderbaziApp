package com.example.CoderBazi.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Setter
@Getter
public class Response {
    private HttpStatus status;
    private String message;
    private Object result;
}
