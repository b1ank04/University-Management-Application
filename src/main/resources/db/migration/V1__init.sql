CREATE TABLE audiences(
    id int PRIMARY KEY
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
                         FOREIGN KEY(group_id) references groups(id) ON DELETE CASCADE
);

CREATE TABLE teachers(
                         id SERIAL PRIMARY KEY,
                         subject_id int,
                         first_name varchar(50),
                         last_name varchar(50),
                         FOREIGN KEY(subject_id) references subjects(id) ON DELETE CASCADE
);
/*
CREATE TABLE lessons(
                        id SERIAL PRIMARY KEY,
                        subject_id int,
                        audience_id int,
                        group_id int,
                        teacher_id int,
                        date date,
                        number int,
                        FOREIGN KEY(group_id) references groups(id) ON DELETE CASCADE,
                        FOREIGN KEY(subject_id) references subjects(id) ON DELETE CASCADE,
                        FOREIGN KEY(teacher_id) references teachers(id) ON DELETE CASCADE,
                        FOREIGN KEY(audience_id) references audiences(id) ON DELETE CASCADE
);

 */
