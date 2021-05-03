package com.hd.test.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.midi.Soundbank;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.hd.test.mapper.EmployeeMapper;
import com.hd.test.pojo.Employee;

import org.junit.jupiter.api.Test;
import sun.print.resources.serviceui;



/**
 * 1、SqlSession代表和数据库的一次会话，用完必须关闭
	2、SqlSession和connection一样都是非线程安装的，每次使用都应该去获取新对象
	3、mapper接口没有实现累，但是mybatis会为这个接口生成一个代理对象
	（将接口和xml进行绑定）
	EmployeeMapper empMapper = sqlSession.getMapper(EmployeeMapper.class)
	4、连个重要的配置文件：
	mybatis的全局配置文件：包含数据库连接信息，事物管理等系统环境信息
	sql映射文件：保存了每一个sql语句的映射信息：
		将sql抽取出来。
 *
 */
public class TestMybatis {
	
	@Test
	public void test() throws IOException {
		// 1、根据mybatis全局配置文件，获取SqlSessionFactory
		String resource = "mybatis-config.xml";
		// 使用MyBatis提供的Resources类加载mybatis的配置文件，获取输入流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 构建sqlSession的工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		// 2、从SqlSession工厂中，获取sqlsession，用来执行sql
		SqlSession session = sqlSessionFactory.openSession();
		try {
			// 查询selectOne
			// @param statement Unique identifier matching the statement to use.  	一个唯一标识
			// @param parameter A parameter object to pass to the statement.		参数
			Employee employee = (Employee) session.selectOne("com.hd.test.mapper.EmployeeMapper.getEmployeeById", 1);
			// 输出信息
			System.out.println(employee);
		} finally {
			// 关闭session
			session.close();
		}
	}
	
	
	/**
	 * 接口式编程
	 * @throws IOException
	 */
	@Test
	public void test2() throws IOException {
		// 1、根据mybatis全局配置文件，获取SqlSessionFactory
		String resource = "mybatis-config.xml";
		// 使用MyBatis提供的Resources类加载mybatis的配置文件，获取输入流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 构建sqlSession的工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		// 2、从SqlSession工厂中，获取sqlsession，用来执行sql
		// sqlsession 非线程安装，每次使用需要区从新获取
		SqlSession session = sqlSessionFactory.openSession();
		try {
			// 3、获取接口的实现对象
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			Employee employee = mapper.getEmployeeById(1);
			// 输出信息
			System.out.println(mapper);
			System.out.println(employee);
		} finally {
			// 关闭session
			session.close();
		}
	}
	
	@Test
	public void test3() throws IOException {
		// 1、根据mybatis全局配置文件，获取SqlSessionFactory
		String resource = "mybatis-config.xml";
		// 使用MyBatis提供的Resources类加载mybatis的配置文件，获取输入流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 构建sqlSession的工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		// 2、从SqlSession工厂中，获取sqlsession，用来执行sql
		SqlSession session = sqlSessionFactory.openSession();
		try {
			// 3、获取接口的实现对象
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			Employee employee = mapper.getEmployeeById3(1);
			// 输出信息
			System.out.println(mapper);
			System.out.println(employee);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}finally {
			// 关闭session
			session.close();
		}
	}
	/**
	 * 测试增删改
	 * 1、mybatis允许增删改直接定义一下类型返回值
	 * 		Ingteger、Long、Boolean
	 * 2、需要手动提交数据
	 * 		SqlSession session = sqlSessionFactory.openSession(); ==> 手动提交
	 * 		SqlSession session = sqlSessionFactory.openSession(true); ==> 自动提交
	 * @throws IOException
	 */
	@Test
	public void test4() throws IOException {
		
		// 获取SqlSessionFactory
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		
		// 获取的sqlsession不会自动提交数据
		SqlSession session = sqlSessionFactory.openSession();
		try {
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			
			// 1、插入数据
			Employee employee = new Employee("小红", "1", "xiaohong@163.com");
			Integer l = mapper.insertEmployee(employee);
			System.out.println(l);
			System.out.println(employee.getId());
			
			// 2、更新数据
//			Employee employee = new Employee(3, "小红", "0", "xiaohong@163.com");
//			boolean f = mapper.updateEmployee(employee);
//			System.out.println(f);
			
			// 3、删除数据
			// mapper.deleteEmployeeById(3);
			
			// 手动提交数据
			session.commit();
		
		} finally {
			session.close();
		}
	}
	
	@Test
	public void test5() throws IOException {
		
		// 获取SqlSessionFactory
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		// 获取的sqlsession自动提交数据
		SqlSession session = sqlSessionFactory.openSession(true);
		try {
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			
			// 插入数据
			Employee employee = new Employee("小黑", "1", "xiaoHei@163.com");
			Integer returnValue = mapper.insertEmployee(employee);
			System.out.println("插入小黑返回值：" + returnValue);
			System.out.println("插入后小黑对象：" + employee);
			
			Employee employee2 = new Employee("小白", "1", "xiaoBai@163.com");
			Integer returnValue2 = mapper.insertEmployeeReturnId(employee2);
			System.out.println("插入小白返回值：" + returnValue2);
			System.out.println("插入后小白对象：" + employee2);
			
		} finally {
			session.close();
		}
	}
	 
	@Test
	public void test6() throws IOException {
		
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sessionFactory.openSession();
		
		try {
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			Map<String, Object> map = new HashMap<String, Object>();
			//map.put("id", 1);
			//Employee employee = mapper.getEmployeeByMap(map);
			// System.out.println(employee);
		} finally {
			session.close();
		}
	}
	
	
	
	@Test
	public void test11() throws IOException {
		
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sessionFactory.openSession();
		
		try {
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			List<Employee> list = mapper.testConditionIf(new Employee(null, "0", null));
			System.out.println(list.size());
			for (Employee employee : list) {
				System.out.println(employee);
			}
		} catch (Exception e) {
			e.printStackTrace();  
		} finally {
			session.close();
		}
	}
	
	@Test
	public void test12() throws IOException {
		
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sessionFactory.openSession();
		
		try {
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			List<Employee> list = mapper.testConditionIfWhere(new Employee("小红", "1", null));
			System.out.println(list.size());
			for (Employee employee : list) {
				System.out.println(employee);
			}
		} catch (Exception e) {
			e.printStackTrace();  
		} finally {
			session.close();
		}
	}
	
	
	@Test
	public void test123() throws IOException {
		
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sessionFactory.openSession();
		
		try {
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			List<Employee> list = mapper.testConditionIfTrim(new Employee(1, null, null, null));
			System.out.println(list.size());
			for (Employee employee : list) {
				System.out.println(employee);
			}
		} catch (Exception e) {
			e.printStackTrace();  
		} finally {
			session.close();
		}
	}
	
	@Test
	public void test1232() throws IOException {
		
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sessionFactory.openSession();
		
		try {
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			List<Employee> list = mapper.testConditionChoose(new Employee(1, null, null, null));
			System.out.println(list.size());
			for (Employee employee : list) {
				System.out.println(employee);
			}
		} catch (Exception e) {
			e.printStackTrace();  
		} finally {
			session.close();
		}
	}
	
	@Test
	public void test13() throws IOException {
		
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sessionFactory.openSession();
		
		try {
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			List<Employee> list = mapper.testConditionForeach(Arrays.asList(1, 2, 3, 4));
			System.out.println(list.size());
			for (Employee employee : list) {
				System.out.println(employee);
			}
		} catch (Exception e) {
			e.printStackTrace();  
		} finally {
			session.close();
		}
	}
	
	@Test
	public void test14() throws IOException {
		
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sessionFactory.openSession();
		
		try {
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			boolean f = mapper.testConditionSet(new Employee(1, "小白", "1", null));
			System.out.println(f);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();  
		} finally {
			session.close();
		}
	}
	@Test
	public void test15() throws IOException {
		
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sessionFactory.openSession();
		
		try {
			EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
			List<Employee> list = mapper.testInnerParameter("employee");
			System.out.println(list.size());
			for (Employee employee : list) {
				System.out.println(employee);
			}
			
		} catch (Exception e) {
			e.printStackTrace();  
		} finally {
			session.close();
		}
	}
	
	
	// 原理
	/**
	 * 1、获取sqlSessionFactory对象:
	 * 		解析文件的每一个信息保存在Configuration中，返回包含Configuration的DefaultSqlSession；
	 * 		注意：【MappedStatement】：代表一个增删改查的详细信息
	 * 
	 * 2、获取sqlSession对象
	 * 		返回一个DefaultSQlSession对象，包含Executor和Configuration;
	 * 		这一步会创建Executor对象；
	 * 
	 * 3、获取接口的代理对象（MapperProxy）
	 * 		getMapper，使用MapperProxyFactory创建一个MapperProxy的代理对象
	 * 		代理对象里面包含了，DefaultSqlSession（Executor）
	 * 4、执行增删改查方法
	 * 
	 * 总结：
	 * 	1、根据配置文件（全局，sql映射）初始化出Configuration对象
	 * 	2、创建一个DefaultSqlSession对象，
	 * 		他里面包含Configuration以及
	 * 		Executor（根据全局配置文件中的defaultExecutorType创建出对应的Executor）
	 *  3、DefaultSqlSession.getMapper（）：拿到Mapper接口对应的MapperProxy；
	 *  4、MapperProxy里面有（DefaultSqlSession）；
	 *  5、执行增删改查方法：
	 *  		1）、调用DefaultSqlSession的增删改查（Executor）；
	 *  		2）、会创建一个StatementHandler对象。
	 *  			（同时也会创建出ParameterHandler和ResultSetHandler）
	 *  		3）、调用StatementHandler预编译参数以及设置参数值;
	 *  			使用ParameterHandler来给sql设置参数
	 *  		4）、调用StatementHandler的增删改查方法；
	 *  		5）、ResultSetHandler封装结果
	 *  注意：
	 *  	四大对象每个创建的时候都有一个interceptorChain.pluginAll(parameterHandler);
	 * 
	 * @throws IOException
	 */
}



