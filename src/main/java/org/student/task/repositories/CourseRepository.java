package org.student.task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.student.task.entities.Course;
import org.student.task.entities.Faculty;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query(value = "FROM Course u WHERE u.name = :name")
    List<Course> getByName(@Param("name") String name) ;
}
