package com.example.CoderBazi.controller;

import com.example.CoderBazi.payload.Response;
import com.example.CoderBazi.services.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "api/")
public class SubmissionController {
    private SubmissionService submissionService;

    @Autowired
    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping("addSubmission/")
    public ResponseEntity<Response> AddSubmission(@RequestParam(value = "user-name") String userName,
                                                  @RequestParam(value = "question-id") int questionId,
                                                  @RequestParam(value = "file") MultipartFile submissionFile) {
        try {
            return new ResponseEntity<>(submissionService.AddSubmission(userName, questionId, submissionFile), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(401, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
