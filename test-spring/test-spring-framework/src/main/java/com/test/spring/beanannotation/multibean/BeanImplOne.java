package com.test.spring.beanannotation.multibean;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(3)
@Component
public class BeanImplOne implements BeanInterface {

}
