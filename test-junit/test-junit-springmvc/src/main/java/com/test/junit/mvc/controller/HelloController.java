package com.test.junit.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hello World";
    }

    @RequestMapping("/map")
    @ResponseBody
    public Map map(){
        Map<String, String> map = new HashMap<>();
        map.put("name", "xiaoming");
        map.put("age", "18");
        return map;
    }

    @RequestMapping(value="/getView")
    public ModelAndView getView(HttpServletRequest request){
        System.out.println("getView");
        ModelAndView modelAndView = new ModelAndView("test-jsp");
        return modelAndView;
    }

}
