package com.example.CoderBazi.controller;


import com.example.CoderBazi.entities.Question;
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
    public ResponseEntity<Response> AddTestCase(@RequestParam(value = "question-id") int questionId,
                                                @RequestParam(value = "test-file") MultipartFile testFile) {
        try {
            return new ResponseEntity<>(testCaseService.AddTestCase(questionId, testFile), HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>(new Response(401, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getTestCase/")
    public ResponseEntity<Resource> GetTestCase(@RequestParam(value = "question-id") int questionId) {
        TestCase testCase = testCaseService.GetTestCase(questionId);
        String fileName = "test_case_file_for_problem/"+questionId+".txt";
        ByteArrayInputStream byteArrayOutputStream;
        byteArrayOutputStream = new ByteArrayInputStream(testCase.getTestFile());
        InputStreamResource fileInputStream = new InputStreamResource(byteArrayOutputStream);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.set(HttpHeaders.CONTENT_TYPE, ContentType.TEXT.getMimeType());
        return new ResponseEntity<>(fileInputStream, headers, HttpStatus.OK);
    }
}
