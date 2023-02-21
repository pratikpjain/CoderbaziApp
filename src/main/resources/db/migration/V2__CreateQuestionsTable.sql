drop table if exists questions cascade;

create table questions (
    question_id int AUTO_INCREMENT,
    user_name varchar(255) not null,
    file BLOB not null,
    primary key (question_id),
    FOREIGN KEY (user_name) REFERENCES users(user_name)
);