package com.example.CoderBazi.repositories;


import com.example.CoderBazi.entities.Question;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    @Transactional
    void deleteAllByQuestionId(int questionId);
}
