package com.test.jpa.service;

import com.test.jpa.dao.StudentDao;
import com.test.jpa.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
public class StudentService {

    @Autowired
    StudentDao studentDao;


    public void addStudent(Student student) {
        studentDao.save(student);
    }
}
