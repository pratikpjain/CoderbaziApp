package com.example.CoderBazi.services;

import com.example.CoderBazi.entities.TestCase;
import com.example.CoderBazi.payload.Response;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface TestCaseService {
    Response AddTestCase(int questionId, MultipartFile testFile) throws IOException;
    TestCase GetTestCase(int questionId);
}
