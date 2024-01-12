-- Insert sample data for Course
INSERT INTO courses (id, name) VALUES (1, 'Computer Science');
INSERT INTO courses (id, name) VALUES (2, 'Mathematics');
INSERT INTO courses (id, name) VALUES (3, 'Physics');

-- Insert sample data for Faculty
INSERT INTO faculties (id, name) VALUES (1, 'Engineering');
INSERT INTO faculties (id, name) VALUES (2, 'Science');
INSERT INTO faculties (id, name) VALUES (3, 'Arts');

-- Insert sample data for Student
INSERT INTO students (id, name, faculty_id, faculty_number) VALUES (1, 'John Doe', 1, 'ABC123');
INSERT INTO students (id, name, faculty_id, faculty_number) VALUES (2, 'Jane Smith', 2, 'XYZ456');
INSERT INTO students (id, name, faculty_id, faculty_number) VALUES (3, 'Bob Johnson', 1, 'DEF789');
INSERT INTO students (id, name, faculty_id, faculty_number) VALUES (4, 'Alice Brown', 3, 'GHI012');

