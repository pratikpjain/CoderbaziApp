package com.example.CoderBazi.services.impl;

import com.example.CoderBazi.entities.Question;
import com.example.CoderBazi.payload.Response;
import com.example.CoderBazi.repositories.QuestionRepository;
import com.example.CoderBazi.services.QuestionService;
import com.j256.simplemagic.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public Response AddQuestion(String userName, MultipartFile file) {
        try {
            if (!ValidateFileType(file)) {
                return new Response(HttpStatus.BAD_REQUEST, "Please upload pdf file only. We do not support other extensions.", null);
            }
            Question question = new Question();
            question.setUserName(userName);
            question.setFile(file.getBytes());
            questionRepository.save(question);
            return new Response(HttpStatus.CREATED, "Question added to the database successfully", null);
        } catch (Exception e) {
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
    }
    @Override
    public Question GetQuestion(int questionId) {
        try {
            Question question = questionRepository.findById(questionId).get();
            return question;
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public Response DeleteQuestion(int questionId) {
        try {
            questionRepository.deleteAllByQuestionId(questionId);
            return new Response(HttpStatus.OK, "Question is Deleted Successfully", null);
        } catch (Exception e) {
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
    }
    boolean ValidateFileType(MultipartFile file) {return file.getContentType().equals(ContentType.PDF.getMimeType());}

}
