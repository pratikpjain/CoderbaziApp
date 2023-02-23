package com.example.CoderBazi.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="testcases")
public class TestCase {
    @Id
    @Column(name="test_case_id")
    private int testCaseId;
    @Lob
    private byte[] testFile;
    private String userName;
    private int questionId;
    private Date createdAt;
    private Date updatedAt;
}
