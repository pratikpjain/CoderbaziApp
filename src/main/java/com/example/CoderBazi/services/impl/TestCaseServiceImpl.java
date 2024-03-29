package com.example.CoderBazi.services.impl;
import com.example.CoderBazi.entities.TestCase;
import com.example.CoderBazi.payload.Response;
import com.example.CoderBazi.repositories.TestCaseRepository;
import com.example.CoderBazi.services.TestCaseService;
import com.j256.simplemagic.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class TestCaseServiceImpl implements TestCaseService {
    private TestCaseRepository testCaseRepository;
    @Autowired
    public TestCaseServiceImpl(TestCaseRepository testCaseRepository) {
        this.testCaseRepository = testCaseRepository;
    }

    @Override
    public Response AddTestCase(String userName, int questionId, MultipartFile testFile) throws IOException {
        try {
            if (!ValidateTestFileType(testFile)) {
                return new Response(HttpStatus.BAD_REQUEST, "We only support Text(.txt) files for test cases. Please upload only text files!", null);
            }
            if (testCaseRepository.isTestCaseExist(questionId) > 0) {
                testCaseRepository.updateTestCase(testFile.getBytes(), questionId);
                return new Response(HttpStatus.CREATED, "Test File is updated successfully", null);
            }
            TestCase testCase = new TestCase();
            testCase.setQuestionId(questionId);
            testCase.setTestFile(testFile.getBytes());
            testCase.setUserName(userName);
            testCaseRepository.save(testCase);
            return new Response(HttpStatus.CREATED, "Test file is uploaded to the database!", null);
        } catch (Exception e) {
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
    }

    @Override
    public TestCase GetTestCase(int questionId) {
        try {
            TestCase testCase = testCaseRepository.findByQuestionId(questionId);
            return testCase;
        } catch (Exception e) {
            return null;
        }
    }

    private boolean ValidateTestFileType(MultipartFile file) {
        return file.getContentType().equals(ContentType.TEXT.getMimeType());
    }
}

