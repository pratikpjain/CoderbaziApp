package com.example.CoderBazi.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private int questionId;
}
