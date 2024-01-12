package org.student.task.services.faculties;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.student.task.entities.Faculty;
import org.student.task.repositories.FacultyRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService{
    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public boolean create(Faculty faculty) {
        this.facultyRepository.save(faculty);

        return true;
    }

    @Override
    public Collection<Faculty> getAll() {
        return this.facultyRepository.findAll();
    }

    @Override
    public Optional<Faculty> getByName(String name) {
        return this.facultyRepository.getByName(name).stream().findFirst();
    }

}
