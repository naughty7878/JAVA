package com.test.jpa.dao;

import com.test.jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentDao extends JpaRepository<Student, Long> {

    // SpringData 会根据方法命名的规则，自动生成查询语句
    List<Student> findByStudentName(String name);

    // 使用@Query注解 可以自定义 JPQL 语句实现更灵活查询
    @Query("SELECT s FROM Student s WHERE s.id = (SELECT max(p2.id) FROM Student p2)")
    Student getMaxIdStudent();

    // 参数传递：使用占位符的方式
    @Query("SELECT s FROM Student s WHERE s.studentName = ?1 and s.studentStatus  = ?2")
    List<Student> getByStudentNameAndStatus(String studentName, String status);

    // 参数传递：命名参数的方式
    @Query("SELECT s FROM Student s WHERE s.studentName = :studentName and s.studentStatus  = :status")
    List<Student> getByStudentNameAndStatus2(@Param("status") String status,@Param("studentName") String studentName);


    // 设置 nativeQuery=true
    @Query(value = "SELECT count(*) FROM tb_student", nativeQuery = true)
    Long selectCountNative();


}
