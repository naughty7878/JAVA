package com.test.ajax.cross.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/test")
@CrossOrigin
public class TestController {
	
	private ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value="/get")
    @ResponseBody
    public Map getTest(HttpServletRequest request){
    	Map<String, Object> map = new HashMap();
    	map.put("data", "TestController getTest()");
        return map;
    }
    
    @RequestMapping(value="/getJsonp")
    @ResponseBody
    public String getJsonp(HttpServletRequest request) throws JsonProcessingException{
    	// 与前端约定好回调方法名称，默认是callback
    	String callback = request.getParameter("callback");
    	Map<String, Object> map = new HashMap();
    	map.put("data", "TestController getJsonp()");
    	String ret = callback+"("+ objectMapper.writeValueAsString(map)+")";
        return ret;
    }
    
    
    @RequestMapping(value = "/getCookie", method = RequestMethod.GET)
    @ResponseBody
    public Map getCookie(@CookieValue(value = "cookie1") String cookie1) {
        System.out.println("TestController getCookie()");
        Map<String, Object> map = new HashMap();
    	map.put("data", "getCookie" + cookie1);
        return map;
    }

    @RequestMapping(value = "/getHeader", method = RequestMethod.GET)
    @ResponseBody
    public Map getHeader(
            @RequestHeader("x-header1") String header1,
            @RequestHeader("x-header2") String header2) {
        System.out.println("TestController getHeader()");
        Map<String, Object> map = new HashMap();
    	map.put("data", "getHeader" + header1+header2);
        return map;
    }
}