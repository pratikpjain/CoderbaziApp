package com.example.CoderBazi.controller;

import com.example.CoderBazi.payload.Response;
import com.example.CoderBazi.services.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
            Response response = submissionService.AddSubmission(userName, questionId, submissionFile);
            return new ResponseEntity<>(response, response.getStatus());
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getSubmissionsByUserName/")
    public ResponseEntity<Response> GetSubmissionsByUserName(@RequestParam(value = "user-name") String user_name) {
        try {
            Response response = submissionService.GetSubmissionsByUserId(user_name);
            return new ResponseEntity<>(response, response.getStatus());
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
