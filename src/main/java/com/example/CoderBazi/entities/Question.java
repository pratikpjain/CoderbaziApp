package com.example.CoderBazi.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="questions")
public class Question {
    @Id
    @Column(name="question_id")
    private int questionId;
    private String userName;
    private int isVerified;
    @Lob
    private byte[] file;
    private Date createdAt;
    private Date updatedAt;
}
