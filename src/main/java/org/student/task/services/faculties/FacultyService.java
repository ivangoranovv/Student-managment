package org.student.task.services.faculties;

import org.student.task.entities.Faculty;
import org.student.task.services.BaseCrudService;

import java.util.Optional;

public interface FacultyService extends BaseCrudService<Faculty> {
    Optional<Faculty> getByName(String name);
}
