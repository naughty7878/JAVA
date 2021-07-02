package com.test.second._02_stream_api;

import com.test.Employee;
import com.test.EmployeeData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamAPITest4 {
    
    @Test
    public void test1() {
        int[] nums = {1, 2, 3, 4, 5};
        IntStream intStream = Arrays.stream(nums).map(x -> Integer.valueOf(x * x));
        ArrayList<Object> collect = intStream.collect(ArrayList::new, (arr, val) -> arr.add(val), (k, v) -> System.out.println("k = " + k + "\t v = " + v));
        System.out.println("collect = " + collect);
    }

    @Test
    public void test2() {
        List<Employee> employees = EmployeeData.getEmployees();
        ArrayList<Object> collect = employees.stream().collect(ArrayList::new, (arr, val) -> arr.add(val), ArrayList::addAll);
        System.out.println("employees = " + employees);
        System.out.println("collect = " + collect);
    }
}
