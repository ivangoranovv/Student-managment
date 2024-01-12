package org.student.task.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.student.task.dtos.CreateFacultyDto;
import org.student.task.dtos.FacultyDto;
import org.student.task.dtos.StudentDto;
import org.student.task.entities.Faculty;
import org.student.task.entities.Student;
import org.student.task.services.faculties.FacultyService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

@RestController
public class FacultyController {

    private final FacultyService facultyService;
    private final ModelMapper modelMapper;
    public FacultyController(FacultyService facultyService, ModelMapper modelMapper) {
        this.facultyService = facultyService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value="/faculties")
    public Collection<FacultyDto> all(){
        var entities = this.facultyService.getAll();
        Collection<FacultyDto> dtos = new ArrayList<FacultyDto>();

        for (Faculty entity : entities) {
            var studentDtos = new ArrayList<StudentDto>();

            for (Student student : entity.getStudents()) {
                studentDtos.add(new StudentDto(
                        student.getName(),
                        student.getFacultyNumber(),
                        student.getFaculty().getName()));
            }

            dtos.add(new FacultyDto(entity.getName(), studentDtos));
        }

        return dtos;
    }

    @PostMapping("/faculties")
    boolean newStudent(@RequestBody CreateFacultyDto newFaculty) {
        var newEntity = this.convertToEntity(newFaculty);

        var result = this.facultyService.create(newEntity);

        return result;
    }

    private Faculty convertToEntity(CreateFacultyDto facultyDto) {
        return modelMapper.map(facultyDto, Faculty.class);
    }
}
