package com.test.mvc.controller;

import com.test.mvc.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HelloController {

    @Autowired
    HelloService helloService;

    public HelloController() {
        System.out.println("初始化 ～ HelloController ～");
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        helloService.testAutowired();
        return "Hello World";
    }

    @RequestMapping("/map")
    @ResponseBody
    public Map<String, String> map(){
        Map<String, String> map = new HashMap<>();
        map.put("name", "xiaoming");
        map.put("age", "18");
        return map;
    }

    @RequestMapping(value= {"/getView", "/getView/{id}"})
    public ModelAndView getView(@PathVariable(value = "id", required = false) Integer id, HttpServletRequest request){
        System.out.println("getView");
        ModelAndView modelAndView = new ModelAndView("test-jsp");
        modelAndView.addObject("abc", "123");
        return modelAndView;
    }

    @RequestMapping(value= {"/getParam"})
    public ModelAndView getParam(@RequestParam Integer id, String name){
        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("getView");
        ModelAndView modelAndView = new ModelAndView("test-jsp");
        modelAndView.addObject("abc", "123");
        return modelAndView;
    }

	public static void main(String[] args) {
		System.out.println(1);
	}
}
