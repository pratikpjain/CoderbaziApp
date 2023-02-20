package com.example.CoderBazi.services.impl;

import com.example.CoderBazi.entities.Question;
import com.example.CoderBazi.payload.Response;
import com.example.CoderBazi.repositories.QuestionRepository;
import com.example.CoderBazi.services.QuestionService;
import com.j256.simplemagic.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;

    @Autowired
    public void QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Response AddQuestion(String userName, MultipartFile file) throws IOException {
        Question question = new Question();
        question.setUserName(userName);
        question.setFile(file.getBytes());
        questionRepository.save(question);
        return new Response(201, "Question added to the database successfully", null);
    }
    @Override
    public Question GetQuestion(int questionId) {
        Question question = questionRepository.findById(questionId).get();
        return question;
    }
    boolean ValidateFileType(MultipartFile file) {
        return file.getContentType().equals(ContentType.PDF);
    }

}
