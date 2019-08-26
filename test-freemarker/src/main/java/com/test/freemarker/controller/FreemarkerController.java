package com.test.freemarker.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.test.freemarker.method.SortMethod;

@Controller
@RequestMapping("/freemarker")
public class FreemarkerController {


	@RequestMapping("/first")
	public ModelAndView first() {
		ModelAndView modelAndView = new ModelAndView("first");
		modelAndView.addObject("username", "Hello Freemarker!");
		return modelAndView;
	}
	
	
	@RequestMapping("/getValue")
	public ModelAndView getValue() {
		ModelAndView modelAndView = new ModelAndView("getValue");
		modelAndView.addObject("intValue", 100);
		modelAndView.addObject("stringValue", "Hello World");
		modelAndView.addObject("doubleValue", 12.34);
		modelAndView.addObject("booleanValue", true);
		modelAndView.addObject("dateValue", new Date());
		modelAndView.addObject("nullValue", null);
		
		
		List<String> list = new ArrayList();
		list.add("Java");
		list.add("Python");
		list.add("HTML");
		modelAndView.addObject("list", list);
		
		
		Map<String, String> map = new HashMap();
		map.put("1","Java");
		map.put("2","Python");
		map.put("3","HTML");
		modelAndView.addObject("map", map);
		
		return modelAndView;
	}
	
	
	@RequestMapping("/grammar")
	public ModelAndView grammar() {
		ModelAndView modelAndView = new ModelAndView("grammar");
		modelAndView.addObject("username", "Hello Freemarker!");
		return modelAndView;
	}
	
	
	@RequestMapping("/string")
	public ModelAndView string() {
		ModelAndView modelAndView = new ModelAndView("string");
		return modelAndView;
	}
	
	
	@RequestMapping("/customMethod")
	public ModelAndView customMethod() {
		ModelAndView modelAndView = new ModelAndView("customMethod");
		modelAndView.addObject("sort_int", new SortMethod());
		return modelAndView;
	}
	
	@RequestMapping("/command")
	public ModelAndView command() {
		ModelAndView modelAndView = new ModelAndView("command");
		return modelAndView;
	}
	
	@RequestMapping("/inFunction")
	public ModelAndView inFunction() {
		ModelAndView modelAndView = new ModelAndView("inFunction");
		return modelAndView;
	}
	
	@RequestMapping("/macro")
	public ModelAndView macro() {
		ModelAndView modelAndView = new ModelAndView("macro");
		return modelAndView;
	}
}
 