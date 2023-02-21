package com.example.CoderBazi.repositories;


import com.example.CoderBazi.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
