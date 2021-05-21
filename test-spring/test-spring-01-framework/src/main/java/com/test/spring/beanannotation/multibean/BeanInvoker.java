package com.test.spring.beanannotation.multibean;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BeanInvoker {
	
	@Autowired
	List<BeanInterface> list;
	 
	@Autowired
	Map<String, BeanInterface> map;

	@Autowired
	@Qualifier("beanImplTwo")
	BeanInterface beanInterface;
	
	public void say() {
		System.out.println("=====list");
		if(list != null) {
			for (BeanInterface beanInterface : list) {
				System.out.println(beanInterface.getClass().getName());
			}
		}else {
			System.out.println("List<BeanInterface> list is null !!!");
		}
		
		System.out.println("=====map");
		if(map != null && map.size() > 0) {
			for (Map.Entry<String, BeanInterface> entry : map.entrySet()) {
				System.out.println(entry.getKey() +"==="+ entry.getValue().getClass().getName());
			}
		}else {
			System.out.println("Map<String, BeanInterface> map is null !!!");
		}
		System.out.println("=====beanInterface");
		if(beanInterface != null) {
			System.out.println(beanInterface.getClass().getName());
		}else {
			System.out.println("BeanInterface beanInterface is null !!!");
		}
	}
	
}
