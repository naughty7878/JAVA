package com.hd;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ParseDecription {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 1、使用类加载器加载类
		try {
			Class c = Class.forName("com.hd.TestDescription");
			System.out.println(c);
			
			// 2、找到类上面的注解
			boolean isExist = c.isAnnotationPresent(Description.class);
			
			if(isExist) {
				// 3、拿到注解实例
				Description d = (Description) c.getAnnotation(Description.class);
				System.out.println(d.desc());
				System.out.println(d.age());
			}
			
			// 4、找到方法上的注解
			Method[] ms = c.getMethods();
			for (Method m : ms) {
				boolean isMExist = m.isAnnotationPresent(Description.class);
				if(isMExist) {
					Description d = m.getAnnotation(Description.class);
					System.out.println(d.author());
				}
			}
			
			// 另外一种解析方法
			for (Method m : ms) {
				Annotation[] annotations = m.getAnnotations();
				for (Annotation annotation : annotations) {
					if(annotation instanceof Description) {
						Description d = (Description) annotation;
						System.out.println(d.age());
					}
				}
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
