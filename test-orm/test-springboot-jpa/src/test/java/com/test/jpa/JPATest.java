package com.test.jpa;

import com.test.jpa.entity.Student;
import com.test.jpa.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.IOException;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JPATest {

    @Autowired
    ApplicationContext context;

    @Autowired
    StudentService studentService;

    @Test
    public void test01() {
        // 进行持久化操作
        Student student = new Student();
        student.setStudentName("小白");
        student.setStudentPwd("123");
        student.setStudentStatus("正常");
        student.setCreateDate(new Date());

        studentService.addStudent(student);
    }

    @Test
    public void test() throws IOException {

    }
}

