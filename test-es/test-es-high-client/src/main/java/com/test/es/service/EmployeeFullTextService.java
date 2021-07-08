package com.test.es.service;

import com.test.es.entity.Employee;

import java.io.IOException;
import java.util.List;
import java.util.Map;

// 定义一些数据操作的接口
public interface EmployeeFullTextService {
    // 添加一个数据
    void add(Employee employee) throws IOException;

    // 根据ID检索指定数据
    Employee findById(long id) throws IOException;

    // 修改职位薪资
    void update(Employee employee) throws IOException;

    // 根据ID删除指定位置数据
    void deleteById(long id) throws IOException;

    // 根据关键字检索数据
    List<Employee> searchByKeywords(String keywords) throws IOException;

    // 分页检索
    Map<String, Object> searchByPage(String keywords, int pageNum, int pageSize) throws IOException;

    // scroll分页解决深分页问题
    Map<String, Object> searchByScrollPage(String keywords, String scrollId, int pageSize) throws IOException;

    // 关闭ES连接
    void close() throws IOException;
}