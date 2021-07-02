package com.test.second._03_optional;

import com.test.Employee;
import org.junit.Test;

import java.util.Optional;

public class OptionalTest {
    
    @Test
    public void test1() {
        Optional<Employee> employee = Optional.of(new Employee());
        System.out.println("employee.get() = " + employee.get());
//        System.out.println(Optional.empty().get());

//        System.out.println(Optional.ofNullable(null).get());

        System.out.println(Optional.empty().isPresent());

        Object o = Optional.empty().orElse(new Employee());
        System.out.println("o = " + o);

        Object o1 = Optional.empty().orElseGet(() -> new Employee());
        System.out.println("o1 = " + o1);
    }

    @Test
    public void test2() {
        Optional<Employee> opt = Optional.ofNullable(new Employee(1, "xx"));

        Optional<String> s = opt.map(emp -> emp.getName());
        System.out.println("s = " + s);

        Optional<String> s1 = opt.flatMap(emp -> Optional.ofNullable(emp.getName()));
        System.out.println("s1 = " + s1);
    }
}
