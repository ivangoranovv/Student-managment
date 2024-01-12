package org.student.task.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue
    private Long Id;

    @ManyToOne
    @JoinColumn(name="faculty_id", nullable=false)
    private Faculty faculty;

    @Column(name = "facultyNumber", nullable = false)
    private String facultyNumber;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "course_participating",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> participatingCourses;

    public Student(String name, Faculty faculty, String facultyNumber) {
        this.name = name;
        this.faculty = faculty;
        this.facultyNumber = facultyNumber;
    }
}
