package com.test.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * @author h__d
 * @description
 * @date 2021/10/28
 */
@RestController
public class WebInfoController {

    Logger logger = LoggerFactory.getLogger(WebInfoController.class);

    @RequestMapping("/*")
    @ResponseBody
    public String hello(HttpServletRequest request, @RequestBody(required=false) String body) {
        // 开始打印请求日志
        // 打印请求相关参数
        logger.info("========================================== Request ==========================================");
        // 打印请求 url
        logger.info("URL            : {}", request.getRequestURL().toString());
        // 打印 Http method
        logger.info("HTTP Method    : {}", request.getMethod());
        // 打印请求的 IP
        logger.info("IP             : {}", getIp(request));
        // 打印请求入参
        logger.info("Request Args   : {}", request.getQueryString());
        // 打印请求Body
        logger.info("Request Body   : {}", body);

        return "success";
    }

    public String getIp(HttpServletRequest request)  {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip != null) {
            if (!ip.isEmpty() && !"unKnown".equalsIgnoreCase(ip)) {
                int index = ip.indexOf(",");
                if (index != -1) {
                    return ip.substring(0, index);
                } else {
                    return ip;
                }
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (ip != null) {
            if (!ip.isEmpty() && !"unKnown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }
}
