package com.example.CoderBazi.controller;

import com.example.CoderBazi.entities.Question;
import com.example.CoderBazi.payload.Response;
import com.example.CoderBazi.payload.ResponseFile;
import com.example.CoderBazi.services.QuestionService;
import com.example.CoderBazi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping(path = "api/")
public class QuestionController {
    private final QuestionService questionService;
    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("addQuestion/")
    public ResponseEntity<Response> AddQuestion(@RequestParam(value = "user-name") String userName,
                                                @RequestParam(value = "file")MultipartFile file) {
        try {
            return new ResponseEntity<>(questionService.AddQuestion(userName, file), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(401, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getQuestion/")
    public ResponseEntity<Resource> GetQuestion(@RequestParam(value = "question-id") int questionId) {
        Question question = questionService.GetQuestion(questionId);
        String fileName = "problem/"+questionId;
        ByteArrayInputStream byteArrayOutputStream;
        byteArrayOutputStream = new ByteArrayInputStream(question.getFile());
        InputStreamResource fileInputStream = new InputStreamResource(byteArrayOutputStream);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.set(HttpHeaders.CONTENT_TYPE, "application/pdf");
        return new ResponseEntity<>(fileInputStream, headers, HttpStatus.OK);
    }
}