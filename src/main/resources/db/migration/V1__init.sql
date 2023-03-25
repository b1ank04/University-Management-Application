CREATE TABLE audiences(
    id SERIAL PRIMARY KEY,
    floor int
);
CREATE TABLE faculties(
                          id SERIAL PRIMARY KEY,
                          name varchar(20)
);
CREATE TABLE subjects(
                         id SERIAL PRIMARY KEY ,
                         faculty_id int,
                         name varchar(20),
                         FOREIGN KEY(faculty_id) references faculties(id) ON DELETE CASCADE
);
CREATE TABLE groups(
                       id SERIAL PRIMARY KEY ,
                       faculty_id int,
                       name varchar(5),
                       FOREIGN KEY(faculty_id) references faculties(id) ON DELETE CASCADE
);
CREATE TABLE students(
                         id SERIAL PRIMARY KEY,
                         group_id int,
                         first_name varchar(50),
                         last_name varchar(50),
                         username varchar(50),
                         password varchar(100),
                         FOREIGN KEY(group_id) references groups(id) ON DELETE CASCADE
);

CREATE TABLE teachers(
                         id SERIAL PRIMARY KEY,
                         subject_id int,
                         first_name varchar(50),
                         last_name varchar(50),
                         username varchar(50),
                         password varchar(100),
                         FOREIGN KEY(subject_id) references subjects(id) ON DELETE CASCADE
);
