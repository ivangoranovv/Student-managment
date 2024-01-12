package org.student.task.services.courses;

import org.student.task.entities.Course;
import org.student.task.entities.Faculty;
import org.student.task.entities.Student;
import org.student.task.services.BaseCrudService;

import java.util.List;
import java.util.Optional;

public interface CourseService extends BaseCrudService<Course> {
    Optional<Course> getByName(String name);

    boolean addStudentsToCourse(String name, List<Student> students);

}
