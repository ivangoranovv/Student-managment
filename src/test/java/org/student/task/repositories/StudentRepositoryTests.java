package org.student.task.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.student.task.entities.Student;
import org.student.task.repositories.StudentRepository;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class StudentRepositoryTests {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @Sql("/data.sql") // If you need to load some initial data for testing
    public void testGetByName() {
        // Given
        String studentName = "Sample Student";
        Student student = new Student();
        student.setName(studentName);
        studentRepository.save(student);

        // When
        List<Student> foundStudents = studentRepository.getByName(studentName);

        // Then
        assertEquals(1, foundStudents.size());
        assertEquals(studentName, foundStudents.get(0).getName());
    }

    // Add more tests as needed
}
