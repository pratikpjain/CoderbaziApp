package com.example.CoderBazi.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class SubmissionResponse {
    private int submissionId;
    private int questionId;
    private String userName;
    private String verdict;
    private Date createdAt;
    private Date updatedAt;
    public SubmissionResponse() {

    }
}
