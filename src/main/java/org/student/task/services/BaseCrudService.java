package org.student.task.services;

import org.student.task.entities.Student;

import java.util.Collection;

public interface BaseCrudService<T>{
    boolean create(T t);
    Collection<T> getAll();
}
