package com.test.second._02_stream_api;

import com.test.Employee;
import com.test.EmployeeData;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamAPITest {

    // 获取流
    @Test
    public void test1() {
        List<Employee> list = EmployeeData.getEmployees();

        // 方法一：
        // 获取顺序流
        Stream<Employee> stream01 = list.stream();
        // 并行流
        Stream<Employee> stream02 = list.parallelStream();
        
        // 方法二
        Employee[] employees = list.toArray(new Employee[list.size()]);
        Stream<Object> stream03 = Arrays.stream(employees);

        // 方法三
        Stream<Employee> stream04 = Stream.of(employees[0], employees[1], employees[2]);

        // 方法四
        Stream<Integer> stream05 = Stream.iterate(0, n -> n + 2);

        // 使用
        stream05.limit(10).forEach(System.out::println);
        Stream.generate(Math::random).limit(5).forEach(System.out::println);
    }
}
