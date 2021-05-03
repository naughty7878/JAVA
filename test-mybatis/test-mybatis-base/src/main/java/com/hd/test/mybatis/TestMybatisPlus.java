package com.hd.test.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.hd.test.mapper.DepartmentPlusMapper;
import com.hd.test.mapper.EmployeeMapperPlus;
import com.hd.test.pojo.DepartmentPlus;
import com.hd.test.pojo.Employee;
import com.hd.test.pojo.EmployeePlus;
import org.junit.jupiter.api.Test;

public class TestMybatisPlus {
	
	
	@Test
	public void test01() throws IOException {
		
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			EmployeeMapperPlus mapper = session.getMapper(EmployeeMapperPlus.class);
			Employee employee = mapper.getEmployeeBys(1);
			System.out.println(employee);
			
			// EmployeePlus employeePlus = session.selectOne("com.hd.test.mapper.EmployeeMapperPlus.getEmployeeBys", 1);
			// System.out.println(employeePlus);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
	}

	
	@Test
	public void test02() throws IOException {
		
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			EmployeeMapperPlus mapper = session.getMapper(EmployeeMapperPlus.class);
			EmployeePlus employeePlus = mapper.getMyEmployeeDept(1);
			System.out.println(employeePlus);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
	}
	
	
	@Test
	public void test03() throws IOException {
		
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			EmployeeMapperPlus mapper = session.getMapper(EmployeeMapperPlus.class);
			EmployeePlus employeePlus = mapper.getMyEmployeeByStep(1);
			System.out.println(employeePlus.getLastName());
			System.out.println(employeePlus.getDept());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
	}
	
	@Test
	public void test04() throws IOException {
		
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			DepartmentPlusMapper mapper = session.getMapper(DepartmentPlusMapper.class);
			DepartmentPlus departmentPlus = mapper.getDeparmentAndEmp(1);
			System.out.println(departmentPlus);
			// System.out.println(employeePlus.getDept());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
	}
	
	
	@Test
	public void test05() throws IOException {
		
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			DepartmentPlusMapper mapper = session.getMapper(DepartmentPlusMapper.class);
			DepartmentPlus departmentPlus = mapper.getDeparmentAndEmpMapByStep(1);
			System.out.println(departmentPlus.getDeptName());
			System.out.println(departmentPlus.getEmps());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
	}
	
	
	
	@Test
	public void test06() throws IOException {
		
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			EmployeeMapperPlus mapper = session.getMapper(EmployeeMapperPlus.class);
			EmployeePlus employeePlus = mapper.getMyEmpDis(2);
			System.out.println(employeePlus);
			System.out.println(employeePlus.getDept());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
}
