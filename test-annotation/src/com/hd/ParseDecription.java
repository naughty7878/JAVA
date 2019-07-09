package com.hd;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 解析注解
 * 通过反射获取类，函数或成员上的运行时注解信息，从而实现动态控制程序运行的逻辑
 * 
 * 对于一个类或者接口来说，Class 类中提供了以下一些方法用于反射注解。
	getAnnotation：返回指定的注解
	isAnnotationPresent：判定当前元素是否被指定注解修饰
	getAnnotations：返回所有的注解
	getDeclaredAnnotation：返回本元素的指定注解
	getDeclaredAnnotations：返回本元素的所有注解，不包含父类继承而来的
 * 
 * @author H__D
 * @date 2019-07-09 22:52:42
 *
 */
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
				System.out.println("========parse class annotation=========");
				System.out.println("desc = " + d.desc());
				System.out.println("author = " + d.author());
				System.out.println("age = " + d.age());
			}
			
			// 4、找到方法上的注解
			Method[] ms = c.getMethods();
			for (Method m : ms) {
				boolean isMExist = m.isAnnotationPresent(Description.class);
				if(isMExist) {
					Description d = m.getAnnotation(Description.class);
					System.out.println("========parse method annotation=========");
					System.out.println("desc = " + d.desc());
					System.out.println("author = " + d.author());
					System.out.println("age = " + d.age());
				}
			}
			
			// 另外一种解析方法
			for (Method m : ms) {
				Annotation[] annotations = m.getAnnotations();
				for (Annotation annotation : annotations) {
					if(annotation instanceof Description) {
						System.out.println("========parse method annotation other way=========");
						Description d = (Description) annotation;
						System.out.println("desc = " + d.desc());
						System.out.println("author = " + d.author());
						System.out.println("age = " + d.age());
					}
				}
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
