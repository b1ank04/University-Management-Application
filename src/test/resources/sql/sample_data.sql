INSERT INTO audiences (id, floor) VALUES (1000, 1000);
INSERT INTO audiences (id, floor) VALUES (1001, 888);

INSERT INTO faculties (id, name) VALUES (1000, 'TEST');
INSERT INTO faculties (id, name) VALUES (1001, 'TEST2');

INSERT INTO subjects (id, faculty_id, name) VALUES (1000, 1000, 'TEST');
INSERT INTO subjects (id, faculty_id, name) VALUES (1001, 1001, 'TEST2');

INSERT INTO groups (id, faculty_id, name) values (1000, 1000, 'TEST');
INSERT INTO groups (id, faculty_id, name) values (1001, 1001, 'TEST2');

INSERT INTO students (id, group_id, first_name, last_name) VALUES (1000, 1000, 'John', 'Doe');
INSERT INTO students (id, group_id, first_name, last_name) VALUES (1001, 1001, 'Jane', 'Doe');

INSERT INTO teachers (id, subject_id, first_name, last_name) VALUES (1000, 1000, 'John', 'Smith');
INSERT INTO teachers (id, subject_id, first_name, last_name) VALUES (1001, 1001, 'Jane', 'Smith');

