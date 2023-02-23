package com.example.CoderBazi.repositories;

import com.example.CoderBazi.entities.Solution;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SolutionRepository extends JpaRepository<Solution, Integer> {
    @Query(value = "select test_case_id from testcases where question_id = ?" , nativeQuery = true)
    int getTestCaseId(int questionId);

    @Query(value = "select * from solutions where question_id = ?", nativeQuery = true)
    Solution getSolutionByQuestionId(int questionId);

    @Modifying
    @Transactional
    @Query(value = "update questions set is_verified = 1 where question_id = ?;", nativeQuery = true)
    void verifyQuestion(int questionId);
}
