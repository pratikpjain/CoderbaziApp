package com.example.CoderBazi.services.impl;

import com.example.CoderBazi.entities.Solution;
import com.example.CoderBazi.payload.Response;
import com.example.CoderBazi.repositories.SolutionRepository;
import com.example.CoderBazi.services.SolutionService;
import com.j256.simplemagic.ContentType;
import org.hibernate.engine.transaction.jta.platform.internal.SunOneJtaPlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class SolutionServiceImpl implements SolutionService {
    private SolutionRepository solutionRepository;

    @Autowired
    public SolutionServiceImpl(SolutionRepository solutionRepository) {
        this.solutionRepository = solutionRepository;
    }

    public Response AddSolution(String userName, int questionId, MultipartFile solutionFile) throws IOException {
        if(!ValidateSolutionFileType(solutionFile)) {
            return new Response(HttpStatus.BAD_REQUEST, "We only support Text(.txt) files for solution file. Please upload only text files!", null);
        }
        try {
            int testCaseId = solutionRepository.getTestCaseId(questionId);
            Solution solution = new Solution();
            solution.setQuestionId(questionId);
            solution.setTestCaseId(testCaseId);
            solution.setSolutionFile(solutionFile.getBytes());
            solution.setUserName(userName);
            solutionRepository.save(solution);
            solutionRepository.verifyQuestion(questionId);
            return new Response(HttpStatus.CREATED, "Solution Added Successfully. question_id:" + questionId + " testCaseId:" + testCaseId, null);

        } catch (Exception e) {
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
    }
    @Override
    public Solution GetSolution(int questionId) {
        Solution solution = solutionRepository.getSolutionByQuestionId(questionId);
        return solution;
    }
    private boolean ValidateSolutionFileType(MultipartFile file) {
        return file.getContentType().equals(ContentType.TEXT.getMimeType());
    }
}
