package com.example.CoderBazi.services;

import com.example.CoderBazi.payload.Response;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface SubmissionService {
    public Response AddSubmission(String userName, int questionId, MultipartFile submissionFile) throws IOException;
    public Response GetSubmissionsByUserId(String userName);
}
