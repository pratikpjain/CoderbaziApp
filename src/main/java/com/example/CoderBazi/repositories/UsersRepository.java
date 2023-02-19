package com.example.CoderBazi.repositories;

import com.example.CoderBazi.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
    @Query(value = "select count(*) from users where lower(user_name) = lower(:user_name);",nativeQuery = true)
    public int UserWithUserNameExists(@Param(value = "user_name") String userName);

    @Query(value = "select * from users;",nativeQuery = true)
    public List<User> getUsers();

    @Query(value = "select * from users where lower(user_name) = lower(:user_name) limit 1;",nativeQuery = true)
    public User getUserByUserName(@Param(value = "user_name") String userName);

    @Transactional
    void deleteAllByUserName(String userName);
}
