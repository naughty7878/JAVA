package com.hd.demo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 需求
 * 1、有一张用户表，字段包括用户ID、用户名、昵称、年龄。。等
 * 2、对每个字段或字段的组合条件进行检索、并打印sql
 * 
 * @author H__D
 * @date 2019-07-09 23:13:03
 *
 */
public class Test {
	public static void main(String[] args) {
		Filter f1 = new Filter();
		f1.setId(10);//查询id为10的用户
		
		Filter f2 = new Filter();
		f2.setUserName("lucy");// 模糊查询用户名为lucy的用户
		
		Filter f3 = new Filter();
		f3.setEmail("liu@sina.com,zhang@163.com,888@qq.com");//查询邮箱为其中任意一个的用户
		
		String sql1 = querySql(f1);
		String sql2 = querySql(f2);
		String sql3 = querySql(f3);
		
		System.out.println(sql1);
		System.out.println(sql2);
		System.out.println(sql3);
	}

	/**
	 * 根据对象、获取sql
	 * @param f
	 * @return
	 */
	private static String querySql(Filter f) {
		
		StringBuilder sb = new StringBuilder();
		// 1、获取到class
		Class c = f.getClass();
		// 2、获取table的名字
		boolean exists = c.isAnnotationPresent(Table.class);
		if(!exists) {
			return null;
		}
		Table t = (Table) c.getAnnotation(Table.class);
		String tableName = t.value();
		sb.append("select * from ");
		sb.append(tableName);
		sb.append(" where 1 = 1 ");
		// 3、遍历所有的字段
		Field[] fArray = c.getDeclaredFields();
		
		// 4、处理每个字段对应的sql
		for (Field field : fArray) {
			boolean fExists = field.isAnnotationPresent(Column.class);
			if(!fExists) {
				continue;
			}
			// 4.1、拿到字段的名字
			Column column = field.getAnnotation(Column.class);
			String columnName = column.value();
			
			// 4.2、根据反射方法名，拿到字段的值
			String fieldName = field.getName();
			String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			Object fieldValue = null;
			try {
				Method getMethod = c.getMethod(getMethodName);
				fieldValue = getMethod.invoke(f);
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			// 4.3拼装sql
			if(fieldValue == null
					|| (fieldValue instanceof Integer && (Integer)fieldValue == 0)) {
				continue;
			}
			sb.append(" and ").append(columnName).append(" = ");
			if(fieldValue instanceof String) {
				if(((String) fieldValue).contains(",")) {
					String[] values = ((String) fieldValue).split(",");
					sb.append("in(");
					for (String string : values) {
						sb.append("'").append(string).append("'").append(",");
					}
					sb.deleteCharAt(sb.length()-1);
					sb.append(")");
				}else {
					sb.append("'").append(fieldValue).append("'");
				}
				
			}else {
				sb.append(fieldValue);
			}
			
		}
		return sb.toString();
	}
}
