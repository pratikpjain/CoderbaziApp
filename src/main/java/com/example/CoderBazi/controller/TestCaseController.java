package com.example.CoderBazi.controller;


import com.example.CoderBazi.entities.TestCase;
import com.example.CoderBazi.payload.Response;
import com.example.CoderBazi.services.TestCaseService;
import com.j256.simplemagic.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("api/")
public class TestCaseController {
    private final TestCaseService testCaseService;
    @Autowired
    public TestCaseController(TestCaseService testCaseService) {
        this.testCaseService = testCaseService;
    }

    @PostMapping("addTestCase/")
    public ResponseEntity<Response> AddTestCase(@RequestParam(value = "user-name") String userName,
                                                @RequestParam(value = "question-id") int questionId,
                                                @RequestParam(value = "test-file") MultipartFile testFile) {
        try {
            Response response = testCaseService.AddTestCase(userName, questionId, testFile);
            return new ResponseEntity<>(response, response.getStatus());
        } catch(Exception e) {
            return new ResponseEntity<>(new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getTestCase/")
    public ResponseEntity<Resource> GetTestCase(@RequestParam(value = "question-id") int questionId) {
        try {
            TestCase testCase = testCaseService.GetTestCase(questionId);
            if(testCase == null) {
                return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            String fileName = "test_case_file_for_problem/" + questionId + ".txt";
            ByteArrayInputStream byteArrayOutputStream;
            byteArrayOutputStream = new ByteArrayInputStream(testCase.getTestFile());
            InputStreamResource fileInputStream = new InputStreamResource(byteArrayOutputStream);
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
            headers.set(HttpHeaders.CONTENT_TYPE, ContentType.TEXT.getMimeType());
            return new ResponseEntity<>(fileInputStream, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
