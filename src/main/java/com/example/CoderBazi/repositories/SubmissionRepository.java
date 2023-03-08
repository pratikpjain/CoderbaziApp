package com.example.CoderBazi.repositories;

import com.example.CoderBazi.entities.Submission;
import com.example.CoderBazi.payload.SubmissionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Integer> {
    @Query(value = "select solution_file from solutions where question_id = ? limit 1;", nativeQuery = true)
    public byte[] getOriginalSolution(int questionId);

    @Query(value = "select submission_id from submissions where question_id = ? and user_name = ? order by created_at desc limit 1", nativeQuery = true)
    public int getSubmissionIdByQuestionIdUserName(int questionId, String userName);

    @Query(value = "select * from submissions where user_name = ? order by created_at", nativeQuery = true)
    List<Submission> getSubmissionsByUserId(String userName);
}
