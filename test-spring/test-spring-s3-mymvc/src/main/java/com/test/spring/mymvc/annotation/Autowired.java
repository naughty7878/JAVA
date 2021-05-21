package com.test.spring.mymvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 修饰目标(字段)
@Target(value = ElementType.FIELD)
// 保留策略：运行时有效
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Autowired {

    String value();

}
