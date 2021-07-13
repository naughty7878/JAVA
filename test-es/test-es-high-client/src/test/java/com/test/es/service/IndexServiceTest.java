package com.test.es.service;


import com.test.es.entity.Employee;
import com.test.es.service.impl.EmployeeFullTextServiceImpl;
import com.test.es.service.impl.IndexServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class IndexServiceTest {

    private IndexService indexService;

    @Before
    public void beforeTest() {
        indexService = new IndexServiceImpl();
    }

    @Test
    public void testAdd() throws IOException {
        indexService.add();
    }

    @Test
    public void testGet() throws IOException {
        indexService.get();
    }

    @Test
    public void testDelete() throws IOException {
        indexService.delete();
    }

    @After
    public void afterTest() throws IOException {
        indexService.close();;
    }
}