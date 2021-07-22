package com.test.jpa.service;

import com.test.jpa.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional
@Service
public class StudentService {
    @Autowired
    EntityManager entityManager;

    public void addStudent(Student student) {
        entityManager.persist(student);
    }
}
