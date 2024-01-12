package org.student.task.services.courses;

import org.springframework.stereotype.Service;
import org.student.task.entities.Course;
import org.student.task.entities.Faculty;
import org.student.task.entities.Student;
import org.student.task.repositories.CourseRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{
    private final CourseRepository courseRepository;


    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public boolean create(Course course) {
        this.courseRepository.save(course);

        return true;
    }

    @Override
    public Collection<Course> getAll() {
        return this.courseRepository.findAll();
    }

    @Override
    public Optional<Course> getByName(String name) {
        return this.courseRepository.getByName(name).stream().findFirst();
    }

    @Override
    public boolean addStudentsToCourse(String name, List<Student> students) {
        var course = this.courseRepository.findAll()
                .stream()
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElse(null);

        if (course == null) {
            throw  new NullPointerException();
        }

        course.getStudents().addAll(students);
        var res = this.courseRepository.save(course);

        return true;
    }

}
