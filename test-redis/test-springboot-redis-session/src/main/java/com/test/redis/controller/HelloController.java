package com.test.redis.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    public void hello(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sessionId = request.getSession().getId();
        System.out.println("request.getSession().getAttribute(\"uid\") = " + request.getSession().getAttribute("uid"));
        request.getSession().setAttribute("uid", "xxxx001");
        System.out.println("sessionId = " + sessionId);
        response.getWriter().println("sessionId = " + sessionId);
    }
}
