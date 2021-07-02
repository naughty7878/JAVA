package com.test.second._02_stream_api;

import com.test.Employee;
import com.test.EmployeeData;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamAPITest2 {

    // 中间操作
    @Test
    public void test1() {
        List<Employee> list = EmployeeData.getEmployees();

        // filter 过滤
        list.stream().filter(employee -> employee.getName().length() == 3).forEach(System.out::println);
        System.out.println("------------------------");
        list.parallelStream().filter(employee -> employee.getName().length() == 3).forEach(System.out::println);

        // limit
        list.stream().limit(2).forEach(System.out::println);
    }

    @Test
    public void test2() {
        List<Employee> list = EmployeeData.getEmployees();

        // limit 限制
        list.stream().limit(2).forEach(System.out::println);
        System.out.println("------------------------");
        list.parallelStream().limit(2).forEach(System.out::println);
    }

    @Test
    public void test3() {
        List<Employee> list = EmployeeData.getEmployees();

        // skip 跳过
        list.stream().skip(2).forEach(System.out::println);
//        System.out.println("------------------------");
//        list.parallelStream().skip(2).forEach(System.out::println);
    }

    @Test
    public void test4() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 3, 4, 4, 5, 6);

        // distinct 去重
        stream.distinct().forEach(System.out::println);
    }

    @Test
    public void test5() {
        Stream<Integer> stream = Stream.of(1, 2, 6);

        // map 映射
        stream.map(x -> x * 10).forEach(System.out::println);

        // flatMap --- 相当于集合的addAll方法
        List<Employee> list = EmployeeData.getEmployees();
        Stream<String> stringStream = list.stream().flatMap(emp -> {
            String[] strings = emp.getName().split("");
            return Arrays.stream(strings);
        });
        stringStream.forEach(System.out::println);
    }

    @Test
    public void test7() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 3, 4, 4, 5, 6);

        // 排序
        stream.sorted(Integer::compareTo).forEach(System.out::println);

        EmployeeData.getEmployees().stream().sorted((x, y) -> x.getAge() - y.getAge()).forEach(System.out::println);
    }

    @Test
    public void test8() {
        List<Employee> list = EmployeeData.getEmployees();

        // 匹配
        // allMatch
        boolean b = list.stream().allMatch(emp -> emp.getAge() > 30);
        System.out.println("b = " + b);
        
        // anyMatch
        boolean b1 = list.stream().anyMatch(emp -> emp.getAge() > 30);
        System.out.println("b1 = " + b1);

        // noneMatch
        boolean b2 = list.stream().noneMatch(emp -> emp.getAge() > 30);
        System.out.println("b2 = " + b2);

        // 查找
        // findFirst
        Optional<Employee> first = list.stream().findFirst();
        System.out.println("first = " + first);
        // findAny
        Optional<Employee> any = list.stream().findAny();
        System.out.println("any = " + any);

        // count
        long count = list.stream().count();
        System.out.println("count = " + count);
        // max
        Optional<Employee> max = list.stream().max((x, y) -> x.getAge() - y.getAge());
        System.out.println("max = " + max);
        // min
        Optional<Employee> min = list.stream().min((x, y) -> x.getAge() - y.getAge());
        System.out.println("min = " + min);
    }
}
