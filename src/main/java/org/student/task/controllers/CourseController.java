package org.student.task.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.student.task.dtos.*;
import org.student.task.entities.Course;
import org.student.task.entities.Faculty;
import org.student.task.entities.Student;
import org.student.task.services.courses.CourseService;
import org.student.task.services.students.StudentService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class CourseController {
    private final CourseService courseService;
    private final StudentService studentService;
    private final ModelMapper modelMapper;

    public CourseController(CourseService courseService, StudentService studentService, ModelMapper modelMapper) {
        this.courseService = courseService;
        this.studentService = studentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value="/courses")
    public Collection<CourseDto> all(){
        var entities = this.courseService.getAll();
        Collection<CourseDto> dtos = new ArrayList<CourseDto>();

        for (Course entity : entities) {
            var studentDtos = new ArrayList<StudentDto>();

            for (Student student : entity.getStudents()) {
                studentDtos.add(new StudentDto(
                        student.getName(),
                        student.getFacultyNumber(),
                        student.getFaculty().getName()));
            }

            dtos.add(new CourseDto(entity.getName(), studentDtos));
        }

        return dtos;
    }

    @PostMapping("/courses")
    boolean newCourse(@RequestBody CreateCourseDto newCourse) {
        var newEntity = this.convertToEntity(newCourse);

        var result = this.courseService.create(newEntity);

        return result;
    }

    @PutMapping("/courses/")
    boolean addStudentsToCourse(@RequestBody UpdateCourseDto dto) {
        var filteredStudents = this.studentService
                .getAll()
                .stream()
                .filter(c -> dto.getStudentNames().contains(c.getName()))
                .toList();

        this.courseService.addStudentsToCourse(dto.getName(), filteredStudents);

        return true;
    }

    private Course convertToEntity(CreateCourseDto courseDto) {
        return modelMapper.map(courseDto, Course.class);
    }
}
