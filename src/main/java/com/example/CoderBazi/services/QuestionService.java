package com.example.CoderBazi.services;

import com.example.CoderBazi.entities.Question;
import com.example.CoderBazi.payload.Response;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface QuestionService {
    public Response AddQuestion(String userName, MultipartFile file);
    public Question GetQuestion(int questionId);
    public Response DeleteQuestion(int questionId);
}
