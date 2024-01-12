package org.student.task.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.student.task.entities.Faculty;
import org.student.task.repositories.FacultyRepository;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class FacultyRepositoryTests {

    @Autowired
    private FacultyRepository facultyRepository;

    @Test
    @Sql("/data.sql") // If you need to load some initial data for testing
    public void testGetByName() {
        // Given
        String facultyName = "Sample Faculty";
        Faculty faculty = new Faculty();
        faculty.setName(facultyName);
        facultyRepository.save(faculty);

        // When
        List<Faculty> foundFaculties = facultyRepository.getByName(facultyName);

        // Then
        assertEquals(1, foundFaculties.size());
        assertEquals(facultyName, foundFaculties.get(0).getName());
    }

    // Add more tests as needed
}
