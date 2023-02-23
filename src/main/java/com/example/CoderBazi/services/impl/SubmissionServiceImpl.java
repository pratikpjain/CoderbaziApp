package com.example.CoderBazi.services.impl;

import com.example.CoderBazi.entities.Submission;
import com.example.CoderBazi.payload.Response;
import com.example.CoderBazi.payload.SubmissionResponse;
import com.example.CoderBazi.repositories.SubmissionRepository;
import com.example.CoderBazi.services.SubmissionService;
import com.example.CoderBazi.utils.AppConstants;
import com.j256.simplemagic.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

@Service
public class SubmissionServiceImpl implements SubmissionService {
    private SubmissionRepository submissionRepository;

    @Autowired
    public SubmissionServiceImpl(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    @Override
    public Response AddSubmission(String userName, int questionId, MultipartFile submissionFile) throws IOException {
        if(!ValidateSubmissionFile(submissionFile)) {
            return new Response(401, "We only support Text(.txt) files for solution file. Please upload only text files!", null);
        }
        try {
            String verdict;
            if(CompareSolution(questionId, submissionFile.getBytes()) == true) {verdict = AppConstants.ACCEPTED;}
            else {verdict = AppConstants.WRONG_ANSWER;}
            Submission submission = new Submission();
            Date createAt = new Date();
            Date updateAt = new Date();
            submission.setUserName(userName);
            submission.setQuestionId(questionId);
            submission.setVerdict(verdict);
            submission.setSubmissionFile(submissionFile.getBytes());
            submission.setCreatedAt(createAt);
            submission.setUpdatedAt(updateAt);
            submissionRepository.save(submission);
            int submissionId = submissionRepository.getSubmissionIdByQuestionIdUserName(questionId, userName);
            SubmissionResponse submissionResponse = new SubmissionResponse(submissionId, questionId, userName, verdict, createAt, updateAt);
            return new Response(201, "Submission is Successful", submissionResponse);
        } catch (Exception e) {
            return new Response(400, e.getMessage(), null);
        }
    }

    public boolean CompareSolution(int questionId, byte[] userSubmission) {
        byte[] originalSolution = submissionRepository.getOriginalSolution(questionId);
        return Arrays.equals(userSubmission, originalSolution);
    }

    private boolean ValidateSubmissionFile(MultipartFile file) {
        return file.getContentType().equals(ContentType.TEXT.getMimeType());
    }
}
