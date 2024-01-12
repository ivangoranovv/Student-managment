package org.student.task.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.student.task.dtos.StudentDto;
import org.student.task.entities.Faculty;
import org.student.task.entities.Student;
import org.student.task.services.faculties.FacultyService;
import org.student.task.services.students.StudentService;
import java.util.Arrays;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(StudentController.class)
public class StudentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @MockBean
    private FacultyService facultyService;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    public void testGetAllStudents() throws Exception {
        // Mock the service response
        Mockito.when(studentService.getAll()).thenReturn(Arrays.asList(/* Mock your Student entities here */));

        // Perform the GET request and verify the response
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Mocked Student Name"))
                .andExpect(jsonPath("$[0].facultyName").value("Mocked Faculty Name"));
    }

    @Test
    public void testCreateNewStudent() throws Exception {
        // Mock the Faculty entity
        Faculty mockedFaculty = new Faculty();
        mockedFaculty.setId(1L);  // Set the ID as needed
        mockedFaculty.setName("Mocked Faculty Name");

        // Mock the service response
        Mockito.when(facultyService.getByName(Mockito.anyString())).thenReturn(Optional.of(mockedFaculty));
        Mockito.when(studentService.create(Mockito.any())).thenReturn(true);

        // Perform the POST request and verify the response
        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"New Student Name\", \"facultyName\": \"Faculty Name\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(true));
    }
}
