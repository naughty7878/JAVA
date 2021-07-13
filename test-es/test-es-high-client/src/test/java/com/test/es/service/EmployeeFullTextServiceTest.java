package com.test.es.service;


import com.test.es.entity.Employee;
import com.test.es.service.impl.EmployeeFullTextServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class EmployeeFullTextServiceTest {

    private EmployeeFullTextService employeeFullTextService;

    @Before
    public void beforeTest() {
        employeeFullTextService = new EmployeeFullTextServiceImpl();
    }

    @Test
    public void testAdd() throws IOException {
        Employee employee = new Employee();
        employee.setId(11l);
        employee.setName("James11");
        employee.setJob("html");
        employee.setAge(18);
        employee.setSal(22000d);
        employee.setGender("male");
        employeeFullTextService.add(employee);
    }

    @Test
    public void testFindById() throws IOException {
        Employee employee = employeeFullTextService.findById(1);
        System.out.println("employee = " + employee);
    }

    @Test
    public void testUpdate() throws IOException {
        Employee employee = new Employee();
        employee.setId(11l);
        employee.setName("James22");
        employee.setJob("html");
        employee.setAge(18);
        employee.setSal(22000d);
        employee.setGender("male");
        employeeFullTextService.update(employee);
    }

    @Test
    public void testDeleteById() throws IOException {
        employeeFullTextService.deleteById(11l);
    }

    @Test
    public void testAddBatch() throws IOException {
        Employee employee1 = new Employee();
        employee1.setId(111l);
        employee1.setName("James11");
        employee1.setJob("html");
        employee1.setAge(18);
        employee1.setSal(22000d);
        employee1.setGender("male");

        Employee employee2 = new Employee();
        employee2.setId(112l);
        employee2.setName("James11");
        employee2.setJob("html");
        employee2.setAge(18);
        employee2.setSal(22000d);
        employee2.setGender("male");

        Employee employee3 = new Employee();
        employee3.setId(113l);
        employee3.setName("James11");
        employee3.setJob("html");
        employee3.setAge(18);
        employee3.setSal(22000d);
        employee3.setGender("male");

        employeeFullTextService.addBatch(employee1, employee2, employee3);
    }

    @Test
    public void testDeleteBatch() throws IOException {
        Employee employee1 = new Employee();
        employee1.setId(111l);

        Employee employee2 = new Employee();
        employee2.setId(112l);

        Employee employee3 = new Employee();
        employee3.setId(113l);

        employeeFullTextService.deleteBatch(employee1, employee2, employee3);
    }

    @Test
    public void testSearchByKeywords() throws IOException {
        List<Employee> employees = employeeFullTextService.searchByKeywords("html");
        for (Employee employee : employees) {
            System.out.println("employee = " + employee);
        }
    }

    @Test
    public void testSearchByPage() throws IOException {
        Map<String, Object> map = employeeFullTextService.searchByPage("html", 1, 2);
        Long total = (Long) map.get("total");
        System.out.println("total = " + total);
        List<Employee> employees = (List<Employee>) map.get("content");
        for (Employee employee : employees) {
            System.out.println("employee = " + employee);
        }
    }

    @Test
    public void testSearchByScrollPage() throws IOException {
        Map<String, Object> map = employeeFullTextService.searchByScrollPage("html", "DXF1ZXJ5QW5kRmV0Y2gBAAAAAAAACvMWdzB4VzVGdjVRYmlKZVRYczBOODctZw==", 2);
        String scroll_id = (String) map.get("scroll_id");
        System.out.println("scroll_id = " + scroll_id);
        List<Employee> employees = (List<Employee>) map.get("content");
        for (Employee employee : employees) {
            System.out.println("employee = " + employee);
        }
    }


    @After
    public void afterTest() throws IOException {
        employeeFullTextService.close();;
    }
}