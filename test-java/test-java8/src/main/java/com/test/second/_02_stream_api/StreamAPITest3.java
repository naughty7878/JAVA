package com.test.second._02_stream_api;

import com.test.Employee;
import com.test.EmployeeData;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPITest3 {

    @Test
    public void test1() {
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

    @Test
    public void test2() {
        List<Employee> list = EmployeeData.getEmployees();

        // reduce 归约
        Optional<Integer> reduce = Stream.iterate(1, n -> n + 1).limit(100)
                .reduce((x, y) -> x + y);
        System.out.println("reduce = " + reduce);

    }

    @Test
    public void test3() {
        List<Employee> list = EmployeeData.getEmployees();

        // collect 收集
        List<String> collect = list.stream().map(Employee::getName).collect(Collectors.toList());
        System.out.println("collect = " + collect);

        ArrayList<String> collect1 = list.stream().map(Employee::getName).collect(Collectors.toCollection(ArrayList::new));
        System.out.println("collect1 = " + collect1);
        HashSet<String> collect2 = list.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new));
        System.out.println("collect2 = " + collect2);

        // 平均值
        Double avg = list.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println("avg = " + avg);
        // 总和
        DoubleSummaryStatistics sum = list.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println("sum = " + sum);
        // 最大/最小
        Optional<Employee> min = list.stream().collect(Collectors.minBy((x, y) -> x.getAge() - y.getAge()));
        System.out.println("min = " + min);
        Optional<Employee> max = list.stream().collect(Collectors.maxBy((x, y) -> x.getAge() - y.getAge()));
        System.out.println("max = " + max);
    }


    @Test
    public void test4() {
        List<Employee> list = EmployeeData.getEmployees();

        // group 分组 -- 按年龄分组
        Map<Integer, List<Employee>> ageMap = list.stream().collect(Collectors.groupingBy(Employee::getAge));
        System.out.println("ageMap = " + ageMap);
        ageMap.forEach((k, v) -> System.out.println("k = " + k + "\t v = " + v));

        Map<Boolean, Map<Boolean, List<Employee>>> group2 = list.stream().collect(Collectors.groupingBy(emp -> emp.getName().length() > 3,
                Collectors.groupingBy(emp -> emp.getAge() > 30)));
        System.out.println("group2 = " + group2);
        group2.forEach((k, vV) -> {
            System.out.println("k = " + k + "\t v = " + vV);
            vV.forEach((kk, vv) -> System.out.println("kk = " + kk + "\t vv = " + vv));
        });

    }


    @Test
    public void test5() {
        List<Employee> list = EmployeeData.getEmployees();

        // 分区
        Map<Boolean, List<Employee>> collect = list.stream().collect(Collectors.partitioningBy(employee -> employee.getAge() < 30));
        System.out.println("collect = " + collect);
        collect.forEach((k, v) -> System.out.println("k = " + k + "\t v = " + v));

    }

    @Test
    public void test6() {
        List<Employee> list = EmployeeData.getEmployees();

        // 概要信息
        DoubleSummaryStatistics summary = list.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println("summary = " + summary);
    }

    @Test
    public void test7() {
        List<Employee> list = EmployeeData.getEmployees();

        // 连接
        String joining = list.stream().map(Employee::getName).collect(Collectors.joining("-"));
        System.out.println("joining = " + joining);

        String joining2 = list.stream().map(Employee::getName).collect(Collectors.joining("-","AA", "ZZ"));
        System.out.println("joining2 = " + joining2);
    }
}
