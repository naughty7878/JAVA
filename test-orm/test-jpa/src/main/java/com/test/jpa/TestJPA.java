package com.test.jpa;

import com.test.jpa.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

public class TestJPA {
    public static void main(String[] args) {
        // 1、创建 EntitymanagerFactory
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mysql-jpa");

        // 2、创建 Entitymanager
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // 3、开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        // 4、进行持久化操作
        Student student = new Student();
        student.setStudentName("小白");
        student.setStudentPwd("123");
        student.setStudentStatus("正常");
        student.setCreateDate(new Date());

        entityManager.persist(student);

        // 5、提交事务
        transaction.commit();

        // 6、关闭 Entitymanager
        entityManager.close();

        // 7、关闭 EntitymanagerFactory
        entityManagerFactory.close();
    }
}
