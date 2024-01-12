package org.student.task.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.student.task.dtos.StudentDto;
import org.student.task.entities.Student;
import org.student.task.repositories.StudentRepository;
import org.student.task.services.faculties.FacultyService;
import org.student.task.services.students.StudentService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    private final FacultyService facultyService;

    private final ModelMapper modelMapper;

    public StudentController(StudentService studentService, FacultyService facultyService,ModelMapper modelMapper) {
        this.studentService = studentService;
        this.facultyService = facultyService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value="/students")
    //@RequestMapping(value="/",method=RequestMethod.GET)
    public Collection<StudentDto> all(){
        var entities = this.studentService.getAll();
        Collection<StudentDto> dtos = new ArrayList<StudentDto>();

        for (Student entity : entities) {
            dtos.add(this.convertToDto(entity));
        }

        return dtos;
    }

    @PostMapping("/students")
    boolean newStudent(@RequestBody StudentDto newStudent) {
        var newEntity = this.convertToEntity(newStudent);
        var faculty = this.facultyService.getByName(newStudent.getFacultyName());

        if (faculty.isEmpty()) {
            throw new NullPointerException();
        }

        newEntity.setFaculty(faculty.get());

        return this.studentService.create(newEntity);
    }

    private StudentDto convertToDto(Student student) {
        return modelMapper.map(student, StudentDto.class);
    }

    private Student convertToEntity(StudentDto studentDto) {
        return modelMapper.map(studentDto, Student.class);
    }
}
