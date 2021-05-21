package com.test.spring.mymvc.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.spring.mymvc.annotation.Controller;
import com.test.spring.mymvc.annotation.RequestMapping;
import com.test.spring.mymvc.annotation.RequestParam;
import com.test.spring.mymvc.annotation.ResponseBody;
import com.test.spring.mymvc.context.WebApplicationContext;
import com.test.spring.mymvc.handler.MyHandler;
import sun.rmi.server.Dispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DispatcherServlet extends HttpServlet {

    // 指定SpringMVC容器
    private WebApplicationContext webApplicationContext;

    // 映射关系集合
    private List<MyHandler> handlerList = new ArrayList<>();

    // json处理对象
    ObjectMapper objectMapper = new ObjectMapper();

    // Servlet初始化方法
    @Override
    public void init() throws ServletException {
        // 1、加载初始化参数
        String contextConfigLocation = this.getServletConfig().getInitParameter("contextConfigLocation");

        // 2、创建SpringMVC容器
        webApplicationContext = new WebApplicationContext(contextConfigLocation);

        // 3、初始化容器
        webApplicationContext.refresh();

        // 4、初始化请求映射关系  /findUser --> 方法
        initHandlerMapping();

    }

    /**
     * 初始化请求映射关系
     */
    private void initHandlerMapping() {
        webApplicationContext.iocMap.forEach((k, v) -> {
            Object bean = v;
            // 获取bean的class类型
            Class<?> clazz = bean.getClass();
            if (clazz.isAnnotationPresent(Controller.class)) {
                // 获取bean中所有的方法
                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(RequestMapping.class)) {
                        RequestMapping requestMappingAn = method.getAnnotation(RequestMapping.class);
                        // 得到映射url
                        String url = requestMappingAn.value();

                        MyHandler myHandler = new MyHandler(url, bean, method);
                        handlerList.add(myHandler);
                    }
                }
            }
        });
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 进行请求分发处理
        doDispatcher(req, resp);
    }

    /**
     * 进行请求分发处理
     *
     * @param req
     * @param resp
     */
    private void doDispatcher(HttpServletRequest req, HttpServletResponse resp) {
        // 根据用户请求地址 查找 handler
        MyHandler myHandler = getHandler(req);
        try {
            if (myHandler == null) {
                resp.getWriter().print("<h1>404 NOT FOUND!</h1>");
            } else {
                // 调用处理方法前，进行参数注入
                Object[] args = resolveArgument(req, myHandler.getMethod());

                // 调用目标方法
                Object result = myHandler.getMethod().invoke(myHandler.getController(), args);

                // 处理返回结果
                processDispatchResult(req, resp, result, myHandler.getMethod());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 处理返回结果
     *
     * @param req
     * @param resp
     * @param result
     */
    private void processDispatchResult(HttpServletRequest req, HttpServletResponse resp, Object result, Method method) {

        try {

            if (result instanceof String) {
                // 视图名
                String viewName = (String) result;
                if (viewName.contains(":")) {
                    String[] names = viewName.split(":");
                    String viewType = names[0];
                    String viewPage = names[1];
                    if (viewType.equals("forward")) {
                        // 转发 forward:/user.jsp
                        req.getRequestDispatcher(viewPage).forward(req, resp);
                    } else {
                        // 重定向 redirect:/user.jsp
                        resp.sendRedirect(viewPage);
                    }
                } else {
                    // 默认转发
                    req.getRequestDispatcher(viewName).forward(req, resp);
                }
            } else {
                // 返回json数据
                if (method.isAnnotationPresent(ResponseBody.class)) {
                    String json = objectMapper.writeValueAsString(result);
                    resp.setContentType("text/html;charset=utf-8");
                    PrintWriter writer = resp.getWriter();
                    writer.print(json);
                    writer.flush();
                    writer.close();
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 解析参数
     */
    private Object[] resolveArgument(HttpServletRequest req, Method method) {
        // 获取方法的参数列表
        Parameter[] parameters = method.getParameters();
        // 获取请求的参数
        Map<String, String[]> parameterMap = req.getParameterMap();
        // 保存参数值
        Object[] paramValues = new Object[parameters.length];

        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            // 方法一：通过注解获取方法名
            if (parameter.isAnnotationPresent(RequestParam.class)) {
                RequestParam requestParamAn = parameter.getAnnotation(RequestParam.class);
                // @RequestParam注解有值
                if (requestParamAn.value().length() > 0) {
                    if(parameter.getType().isArray()) {
                        // 数组参数
                        paramValues[i] = parameterMap.get(requestParamAn.value());
                    }else {
                        // 普通参数
                        // 使用注解参数名
                        paramValues[i] = parameterMap.get(requestParamAn.value())[0];
                    }
                    continue;
                }
            }
            // 方法二，通过Java8中parameter来获取参数名
            if(parameter.getType().isArray()) {
                // 数组参数
                paramValues[i] = parameterMap.get(parameter.getName());
            }else {
                // 普通参数
                // 使用注解参数名
                paramValues[i] = parameterMap.get(parameter.getName())[0];
            }
        }
        return paramValues;
    }

    /**
     * 获取请求对应的handler
     *
     * @param req
     * @return
     */
    private MyHandler getHandler(HttpServletRequest req) {
        String uri = req.getServletPath();
        for (MyHandler myHandler : handlerList) {
            if (myHandler.getUrl().equals(uri)) {
                return myHandler;
            }
        }
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
