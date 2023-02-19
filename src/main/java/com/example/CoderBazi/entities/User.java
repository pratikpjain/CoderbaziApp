package com.example.CoderBazi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name="users")
public class User {
    private String name;
    @Id
    @Column(name = "user_name")
    private String userName;
    private String phoneNumber;
    private Date createdAt;
    private Date updatedAt;
}
