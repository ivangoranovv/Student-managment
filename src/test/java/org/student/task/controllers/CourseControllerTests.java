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
import org.student.task.controllers.CourseController;
import org.student.task.dtos.CreateCourseDto;
import org.student.task.services.courses.CourseService;
import org.student.task.services.students.StudentService;
import java.util.Arrays;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(CourseController.class)
public class CourseControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @MockBean
    private StudentService studentService;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    public void testGetAllCourses() throws Exception {
        // Mock the service response
        Mockito.when(courseService.getAll()).thenReturn(Arrays.asList(/* Mock your Course entities here */));

        // Perform the GET request and verify the response
        mockMvc.perform(get("/courses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Mocked Course Name"))
                .andExpect(jsonPath("$[0].students[0].name").value("Mocked Student Name"));
    }

    @Test
    public void testCreateNewCourse() throws Exception {
        // Mock the service response
        Mockito.when(courseService.create(Mockito.any())).thenReturn(true);

        // Perform the POST request and verify the response
        mockMvc.perform(post("/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"New Course Name\", \"students\": []}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(true));
    }

    @Test
    public void testAddStudentsToCourse() throws Exception {
        // Mock the service response
        Mockito.when(studentService.getAll()).thenReturn(Arrays.asList(/* Mock your Student entities here */));
        Mockito.when(courseService.addStudentsToCourse(Mockito.anyString(), Mockito.anyList())).thenReturn(true);

        // Perform the PUT request and verify the response
        mockMvc.perform(put("/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Course Name\", \"studentNames\": [\"Student1\", \"Student2\"]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(true));
    }
}