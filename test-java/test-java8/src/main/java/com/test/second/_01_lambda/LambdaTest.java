package com.test.second._01_lambda;

import com.test.Employee;
import com.test.EmployeeData;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

public class LambdaTest {

    // 原来的匿名内部类
    @Test
    public void test1() {
        Comparator<Integer> com = new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

    }

    // Lambda 表达式
    @Test
    public void test2() {
        Comparator<Integer> com = (o1, o2) -> {
            return Integer.compare(o1, o2);
        };
    }

    @Test
    public void test3() {
        List<Employee> employees = EmployeeData.getEmployees();
        employees.forEach(employee -> {
            System.out.println(employee.getId() + "\t" + employee.getName());
        });
    }
}
