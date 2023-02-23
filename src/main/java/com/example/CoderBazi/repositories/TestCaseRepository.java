package com.example.CoderBazi.repositories;
import com.example.CoderBazi.entities.TestCase;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCaseRepository extends JpaRepository<TestCase, Integer> {

    @Query(value = "select count(*) from testcases where question_id = ?;", nativeQuery = true)
    int isTestCaseExist(int questionId);

    @Modifying
    @Transactional
    @Query(value = "update testcases set test_file = ? where question_id = ?;", nativeQuery = true)
    void updateTestCase(byte[] testFile, int questionId);

    @Query(value = "select * from testcases where question_id = ?", nativeQuery = true)
    TestCase findByQuestionId(int questionId);
}
