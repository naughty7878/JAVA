package com.hd.test.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.hd.test.mapper.EmployeeMapper;
import com.hd.test.mapper.EmployeeMapperPlus;
import com.hd.test.pojo.Employee;

public class TestMybatisCache {

	/**
	 * * 一级缓存：（本地缓存）：sqlSession级别的缓存。一级缓存是一直开启的；SqlSession级别的一个Map
	 * 		与数据库同一次会话期间查询到的数据会放在本地缓存中。
	 * 		以后如果需要获取相同的数据，直接从缓存中拿，没必要再去查询数据库；
	 * 
	 * 		一级缓存失效情况（没有使用到当前一级缓存的情况，效果就是，还需要再向数据库发出查询）：
	 * 		1、sqlSession不同。
	 * 		2、sqlSession相同，查询条件不同.(当前一级缓存中还没有这个数据)
	 * 		3、sqlSession相同，两次查询之间执行了增删改操作(这次增删改可能对当前数据有影响)
	 * 		4、sqlSession相同，手动清除了一级缓存（缓存清空）
	 * @throws IOException
	 */
	@Test
	public void testFirstLevelCache() throws IOException {
		
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession(true);
		
		try {
			// 一级缓存查询
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			Employee employee = mapper.getEmployeeById(1);
			System.out.println(employee);
			
			Employee employee2 = mapper.getEmployeeById(1);
			System.out.println(employee2);
			
			System.out.println(employee == employee2);
			
			// 清除缓存
			session.clearCache(); 
			Employee employee3 = mapper.getEmployeeById(1);
			System.out.println(employee3);
			
			// cud操作
			Employee newEmploye = new Employee(1, "大白", "1", "dabai@163.com");
			boolean updateEmployee = mapper.updateEmployee(newEmploye);
			Employee employee4 = mapper.getEmployeeById(1);
			System.out.println(employee4);
			System.out.println(employee4 == newEmploye);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	/**
	 * * 二级缓存：（全局缓存）：基于namespace级别的缓存：一个namespace对应一个二级缓存：
	 * 		工作机制：
	 * 		1、一个会话，查询一条数据，这个数据就会被放在当前会话的一级缓存中；
	 * 		2、如果会话关闭；一级缓存中的数据会被保存到二级缓存中；新的会话查询信息，就可以参照二级缓存中的内容；
	 * 		3、sqlSession===EmployeeMapper==>Employee
	 * 						DepartmentMapper===>Department
	 * 			不同namespace查出的数据会放在自己对应的缓存中（map）
	 * 			效果：数据会从二级缓存中获取
	 * 				查出的数据都会被默认先放在一级缓存中。
	 * 				只有会话提交或者关闭以后，一级缓存中的数据才会转移到二级缓存中
	 * 		使用：
	 * 			1）、开启全局二级缓存配置：<setting name="cacheEnabled" value="true"/>
	 * 			2）、去mapper.xml中配置使用二级缓存：
	 * 				<cache></cache>
	 * 			3）、POJO需要实现序列化接口
	 * 
	 * 
	 * @throws IOException
	 */
	@Test
	public void testSecondLevelCache() throws IOException {
		
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
		
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		EmployeeMapper mapper2 = sqlSession2.getMapper(EmployeeMapper.class);
		
		Employee employee01 = mapper.getEmployeeById(1);
		System.out.println("employee01==" + employee01);
		sqlSession.close();
		
		Employee employee02 = mapper2.getEmployeeById(1);
		System.out.println("employee02==" + employee02);
		sqlSession2.close();
		
		System.out.println("employee01 == employee02:\t" + (employee01 == employee02));
		
	}
	
	
	
	/**
	 * * 和缓存有关的设置/属性：
	 * 			1）、cacheEnabled=true：false：关闭缓存（二级缓存关闭）(一级缓存一直可用的)
	 * 			2）、每个select标签都有useCache="true"：
	 * 					false：不使用缓存（一级缓存依然使用，二级缓存不使用）
	 * 			3）、【每个增删改标签的：flushCache="true"：（一级二级都会清除）】
	 * 					增删改执行完成后就会清楚缓存；
	 * 					测试：flushCache="true"：一级缓存就清空了；二级也会被清除；
	 * 					查询标签：flushCache="false"：
	 * 						如果flushCache=true;每次查询之后都会清空缓存；缓存是没有被使用的；
	 * 			4）、sqlSession.clearCache();只是清楚当前session的一级缓存；
	 * 			5）、localCacheScope：本地缓存作用域：（一级缓存SESSION）；当前会话的所有数据保存在会话缓存中；
	 * 								STATEMENT：可以禁用一级缓存；	
	 */
}
