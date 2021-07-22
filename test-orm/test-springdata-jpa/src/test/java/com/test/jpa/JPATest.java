package com.test.jpa;

import com.test.jpa.dao.StudentDao;
import com.test.jpa.entity.Student;
import com.test.jpa.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JPATest {

    @Autowired
    ApplicationContext context;

    @Autowired
    StudentDao studentDao;

    @Test
    public void save() {
        // 进行持久化操作
        Student student = new Student();
        student.setStudentName("小白");
        student.setStudentPwd("123");
        student.setStudentStatus("正常");
        student.setCreateDate(new Date());

        studentDao.save(student);
    }

    @Test
    public void findById()  {
        Optional<Student> student = studentDao.findById(1l);
        System.out.println("student = " + student);
    }

    @Test
    public void findAll() {
        List<Student> students = studentDao.findAll();
        System.out.println("students = " + students);
    }

    @Test
    public void findByStudentName() {
        List<Student> students = studentDao.findByStudentName("小白");
        System.out.println("students = " + students);
    }

    @Test
    public void getMaxIdStudent() {
        Student student = studentDao.getMaxIdStudent();
        System.out.println("student = " + student);
    }

    @Test
    public void getByStudentNameAndStatus() {
        List<Student> students = studentDao.getByStudentNameAndStatus("小白", "正常");
        System.out.println("students = " + students);
    }


    @Test
    public void getByStudentNameAndStatus2() {
        List<Student> students = studentDao.getByStudentNameAndStatus2("正常", "小白");
        System.out.println("students = " + students);
    }


    @Test
    public void selectCountNative() {
        Long count = studentDao.selectCountNative();
        System.out.println("count = " + count);
    }

    @Test
    public void pageFindAll() {
        // pageNo 从0开始
        PageRequest pageRequest = PageRequest.of(1, 2);
        Page<Student> page = studentDao.findAll(pageRequest);
        System.out.println("page.getTotalPages() = " + page.getTotalPages());
        System.out.println("page.getContent() = " + page.getContent());
    }


}

