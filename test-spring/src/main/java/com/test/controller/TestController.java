package com.test.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/testController")
public class TestController {

	@RequestMapping(value="/getView")
	@ResponseBody
	public ModelAndView getTest(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("test-jsp");
		return modelAndView;
	}
}
