package com.example.CoderBazi.services;

import com.example.CoderBazi.entities.Solution;
import com.example.CoderBazi.payload.Response;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface SolutionService {
    public Response AddSolution(String userName, int questionId, MultipartFile solutionFile) throws IOException;
    public Solution GetSolution(int questionId);
}
