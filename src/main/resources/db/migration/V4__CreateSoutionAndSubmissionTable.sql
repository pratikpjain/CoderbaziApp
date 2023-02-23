create table solutions (
    solution_id int AUTO_INCREMENT,
    question_id int,
    test_case_id int,
    user_name varchar(255) not null,
    solution_file BLOB not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    primary key (solution_id),
    FOREIGN KEY (question_id) REFERENCES questions(question_id),
    FOREIGN KEY(test_case_id) REFERENCES testcases(test_case_id),
    FOREIGN KEY (user_name) REFERENCES users(user_name)
);

create table submissions (
    submission_id int AUTO_INCREMENT,
    submission_file BLOB not null,
    user_name varchar(255) not null,
    question_id int,
    verdict varchar(50),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    primary key(submission_id),
    FOREIGN KEY (question_id) REFERENCES questions(question_id),
    FOREIGN KEY (user_name) REFERENCES users(user_name)
);