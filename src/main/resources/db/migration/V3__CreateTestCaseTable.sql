drop table if exists questions cascade;
drop table if exists testcases cascade;

create table questions (
    question_id int AUTO_INCREMENT,
    user_name varchar(255) not null,
    file BLOB not null,
    is_verified int default 0,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    primary key (question_id),
    FOREIGN KEY (user_name) REFERENCES users(user_name)
);

create table testcases (
    test_case_id int AUTO_INCREMENT,
    test_file BLOB not null,
    user_name varchar(255) not null,
    question_id int,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    primary key (test_case_id),
    FOREIGN KEY (question_id) references questions(question_id),
    FOREIGN KEY (user_name) REFERENCES users(user_name)
);