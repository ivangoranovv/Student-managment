package org.student.task.services.students;

import org.springframework.stereotype.Service;
import org.student.task.entities.Student;
import org.student.task.repositories.StudentRepository;
import org.student.task.services.students.StudentService;

import java.util.Collection;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean create(Student student) {
        var result = this.repository.save(student);

        return result != null;
    }

    @Override
    public Collection<Student> getAll() {
        return this.repository.findAll();
    }
}
