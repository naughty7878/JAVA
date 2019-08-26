package com.test.mybatis.generator;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.test.mybatis.dao.EmployeeMapper;
import com.test.mybatis.pojo.Employee;

public class TestMybatis {
	
	public static void main(String[] args) throws IOException {
		
		// 获取SqlSessionFactory
				InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
				SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
				
				System.out.println("==================");
				
				// 获取的sqlsession不会自动提交数据
				SqlSession session1 = sqlSessionFactory.openSession();
				// 获取的sqlsession不会自动提交数据
				SqlSession session2 = sqlSessionFactory.openSession();
				try {
					EmployeeMapper mapper = session1.getMapper(EmployeeMapper.class);
					
					Employee employee01 = mapper.selectByPrimaryKey(1);
					System.out.println(employee01.getId());
					
					EmployeeMapper mapper2 = session2.getMapper(EmployeeMapper.class);
					
					Employee employee02 = mapper2.selectByPrimaryKey(1);
					System.out.println(employee02.getId());

				
				} finally {
					session1.close();
					session2.close();
				}
	}

	@Test
	public  void test() throws IOException {
		
		
	}
	
	
}



