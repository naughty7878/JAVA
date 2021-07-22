package com.test.jpa.entity;

import javax.persistence.*;
import java.util.Date;

// @Entity注解，指定该实体类是一个基于JPA规范的实体类
@Entity
// @Table注解，指定当前实体类关联的表
@Table(name = "tb_student")
public class Student {

    // @Id注解，声明属性为一个ID属性
    @Id
    // @GeneratedValue注解，指定主键生成策略
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column注解，设置属性关联的数据库表字段
    // 注意：如果属性名和表字段名相同，可以不设置
    @Column(name = "student_name")
    private String studentName;
    @Column(name = "student_pwd")
    private String studentPwd;
    @Column(name = "student_status")
    private String studentStatus;
    @Column(name = "create_date")
    private Date createDate;

    // get、set方法
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentPwd() {
        return studentPwd;
    }

    public void setStudentPwd(String studentPwd) {
        this.studentPwd = studentPwd;
    }

    public String getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(String studentStatus) {
        this.studentStatus = studentStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", studentPwd='" + studentPwd + '\'' +
                ", studentStatus='" + studentStatus + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}