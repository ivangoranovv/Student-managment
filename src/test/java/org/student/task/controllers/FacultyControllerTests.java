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
import org.student.task.controllers.FacultyController;
import org.student.task.dtos.CreateFacultyDto;
import org.student.task.services.faculties.FacultyService;
import java.util.Arrays;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(FacultyController.class)
public class FacultyControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyService facultyService;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    public void testGetAllFaculties() throws Exception {
        // Mock the service response
        Mockito.when(facultyService.getAll()).thenReturn(Arrays.asList(/* Mock your Faculty entities here */));

        // Perform the GET request and verify the response
        mockMvc.perform(get("/faculties"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Mocked Faculty Name"))
                .andExpect(jsonPath("$[0].students[0].name").value("Mocked Student Name"));
    }

    @Test
    public void testCreateNewFaculty() throws Exception {
        // Mock the service response
        Mockito.when(facultyService.create(Mockito.any())).thenReturn(true);

        // Perform the POST request and verify the response
        mockMvc.perform(post("/faculties")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"New Faculty Name\", \"students\": []}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(true));
    }
}
