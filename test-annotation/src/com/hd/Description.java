package com.hd;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解的注解：元注解
 * 
 * 注解作用域
	ElementType.TYPE：允许被修饰的注解作用在类、接口和枚举上
	ElementType.FIELD：允许作用在属性字段上
	ElementType.METHOD：允许作用在方法上
	ElementType.PARAMETER：允许作用在方法参数上
	ElementType.CONSTRUCTOR：允许作用在构造器上
	ElementType.LOCAL_VARIABLE：允许作用在本地局部变量上
	ElementType.ANNOTATION_TYPE：允许作用在注解上
	ElementType.PACKAGE：允许作用在包上
	
	注解的生命周期
	RetentionPolicy.SOURCE：当前注解编译期可见，不会写入 class 文件
	RetentionPolicy.CLASS：类加载阶段丢弃，会写入 class 文件
	RetentionPolicy.RUNTIME：永久保存，可以反射获取
 *
 */
// 注解的作用域
@Target({ElementType.METHOD, ElementType.TYPE})
// 注解的生命周期
@Retention(RetentionPolicy.RUNTIME)
// 允许子类继承
@Inherited
// 生成javadoc的时候生成注解的信息
@Documented
// @interface：使用@interface关键定义注解
public @interface Description {
	
	// 注解的成员
	// 成员类型所限的，合法的类型包括原始数据类型及String、Class、Annotation、Enumeration
	String desc();
	
	String author();
	
	// 成员以无参无异常方式生命，可以用default为成员指定一个默认值
	int age() default 18;
	
	// 如果注解成员只有一个时，成员名必须取名未value(),在使用时可以忽略成员名和赋值号(=)
	// 注解可以没有成员，没有成员的注解成为标识注解
}
