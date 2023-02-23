package com.example.CoderBazi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "solutions")
public class Solution {
    @Id
    @Column(name = "solution_id")
    private int solutionId;
    private int questionId;
    private int testCaseId;
    private String userName;
    @Lob
    private byte[] solutionFile;
    private Date createdAt;
    private Date updatedAt;
}
