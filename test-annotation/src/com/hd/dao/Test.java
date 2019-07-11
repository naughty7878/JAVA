package com.hd.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test {
	public static void main(String[] args) {
		Filter f1 = new Filter();
		f1.setId(10);//查询id为10的用户
		
		Filter f2 = new Filter();
		f2.setUserName("lucy");// 模糊查询用户名为lucy的用户
		
		Filter f3 = new Filter();
		f3.setEmail("liu@sina.com,zhang@163.com,888@qq.com");//查询邮箱为其中任意一个的用户
		
		String sql1 = query(f1);
		String sql2 = query(f2);
		String sql3 = query(f3);
		
		System.out.println(sql1);
		System.out.println(sql2);
		System.out.println(sql3);
	}

	private static String query(Filter f) {
		
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
		for (Field field : fArray) {
			// 4、处理每个字段对应的sql
			// 4.1、拿到字段的名字
			boolean fExists = field.isAnnotationPresent(Column.class);
			if(!fExists) {
				continue;
			}
			Column column = field.getAnnotation(Column.class);
			String columnName = column.value();
			// 4.2拿到字段的值
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
