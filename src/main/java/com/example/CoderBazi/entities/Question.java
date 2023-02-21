package com.example.CoderBazi.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="questions")
public class Question {
    @Id
    @Column(name="question_id")
    private int questionId;
    private String userName;
    @Lob
    private byte[] file;
}
