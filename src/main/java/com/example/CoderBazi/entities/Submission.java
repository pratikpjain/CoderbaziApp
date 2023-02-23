package com.example.CoderBazi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "submissions")
public class Submission {
    @Id
    @Column(name = "submission_id")
    private int submissionId;
    private int questionId;
    private String userName;
    private String verdict;
    @Lob
    private byte[] submissionFile;
    private Date createdAt;
    private Date updatedAt;
}
