package com.test.mybatis.plugin;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.mybatis.plugin.dao.EmployeeMapper;
import com.test.mybatis.plugin.pojo.Employee;

public class TestMybatis {

	
	public static void main(String[] args) throws IOException {

		// 获取SqlSessionFactory
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		// System.out.println("==================");

		// 获取的sqlsession不会自动提交数据
		SqlSession session = sqlSessionFactory.openSession();
		try {
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);

			Employee employee01 = mapper.selectByPrimaryKey(1);
			System.out.println(employee01.getId());

		} finally {
			session.close();
		}
	}
	
	
	@Test
	public void test01() throws IOException {
		
		// 获取SqlSessionFactory
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		System.out.println("==================");

		// 获取的sqlsession不会自动提交数据
		SqlSession session = sqlSessionFactory.openSession();
		
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		
		//获取第1页，10条内容，默认查询总数count
		Page<Object> page = PageHelper.startPage(1, 5);
		
		List<Employee> list = mapper.selectByExample(null);
		for(Employee emp: list) {
			System.out.println(emp);
		}
		
		
//		System.out.println("当前页码："+page.getPageNum());
//		System.out.println("总记录数："+page.getTotal());
//		System.out.println("每页的记录数："+page.getPageSize());
//		System.out.println("总页码："+page.getPages());
		
		//用PageInfo对结果进行包装
//		PageInfo pageInfo = new PageInfo(list);
		//传入要连续显示多少页
		PageInfo<Employee> pageInfo = new PageInfo<Employee>(list, 5);
		System.out.println("当前页码："+pageInfo.getPageNum());
		System.out.println("总记录数："+pageInfo.getTotal());
		System.out.println("每页的记录数："+pageInfo.getPageSize());
		System.out.println("总页码："+pageInfo.getPages());
		System.out.println("是否为第一页："+pageInfo.isIsFirstPage());
		int[] nums = pageInfo.getNavigatepageNums();
		for (int i = 0; i < nums.length; i++) {
			System.out.println(nums[i]);
		}
	}

}
