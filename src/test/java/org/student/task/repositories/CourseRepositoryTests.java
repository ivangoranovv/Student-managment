package org.student.task.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.student.task.entities.Course;
import org.student.task.repositories.CourseRepository;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class CourseRepositoryTests {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    @Sql("/data.sql") // If you need to load some initial data for testing
    public void testGetByName() {
        // Given
        String courseName = "Sample Course";
        Course course = new Course();
        course.setName(courseName);
        courseRepository.save(course);

        // When
        List<Course> foundCourses = courseRepository.getByName(courseName);

        // Then
        assertEquals(1, foundCourses.size());
        assertEquals(courseName, foundCourses.get(0).getName());
    }

    // Add more tests as needed
}
