package com.example.CoderBazi.controller;


import com.example.CoderBazi.entities.Solution;
import com.example.CoderBazi.entities.TestCase;
import com.example.CoderBazi.payload.Response;
import com.example.CoderBazi.services.SolutionService;
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
@RequestMapping(value = "api/")
public class SolutionController {

    private SolutionService solutionService;

    @Autowired
    public SolutionController(SolutionService solutionService) {
        this.solutionService = solutionService;
    }

    @PostMapping("addSolution/")
    public ResponseEntity<Response> AddSolution(@RequestParam(value = "user-name") String userName,
                                                @RequestParam(value = "question-id") int questionId,
                                                @RequestParam(value = "file") MultipartFile solutionFile) {
        try {
            return new ResponseEntity<>(solutionService.AddSolution(userName, questionId, solutionFile), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(401, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getSolution/")
    public ResponseEntity<Resource> GetSolution(@RequestParam(value = "question-id") int questionId) {
        try {
            Solution solution = solutionService.GetSolution(questionId);
            String fileName = "solution_file_for_question/" + questionId + ".txt";
            ByteArrayInputStream byteArrayOutputStream;
            byteArrayOutputStream = new ByteArrayInputStream(solution.getSolutionFile());
            InputStreamResource fileInputStream = new InputStreamResource(byteArrayOutputStream);
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
            headers.set(HttpHeaders.CONTENT_TYPE, ContentType.TEXT.getMimeType());
            return new ResponseEntity<>(fileInputStream, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }
    }
}
