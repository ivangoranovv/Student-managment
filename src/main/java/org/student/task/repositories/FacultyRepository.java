package org.student.task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.student.task.entities.Faculty;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    @Query(value = "FROM Faculty u WHERE u.name = :name")
    List<Faculty> getByName(@Param("name") String name) ;
}
