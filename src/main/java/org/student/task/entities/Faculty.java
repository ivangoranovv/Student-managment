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
@Table(name = "faculties")
public class Faculty {

    @GeneratedValue
    @Id
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "faculty")
    private Set<Student> students;

    public Faculty(String name) {
        this.name = name;
    }
}
