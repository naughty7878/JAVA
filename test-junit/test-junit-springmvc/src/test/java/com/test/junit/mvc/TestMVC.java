package com.test.junit.mvc;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;


// SpringRunner 继承了SpringJUnit4ClassRunner，
// 没有扩展任何功能；使用SpringRunner，名字简短而已
@RunWith(SpringRunner.class)
// @RunWith(SpringJUnit4ClassRunner.class)
// Web项目配置
@WebAppConfiguration
// 上下文配置
@ContextConfiguration(locations = {"classpath:spring/spring-mvc.xml", "classpath:spring/spring-context.xml"})
public class TestMVC {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        // 构建mockMvc 对象
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void getView() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(
                // 数据的格式请求的url,请求的方法是get
                MockMvcRequestBuilders.get("/getView")
        );
        // 打印调试结果到控制台
        resultActions.andDo(MockMvcResultHandlers.print());
        // 获取返回结果
        MvcResult mvcResult = resultActions.andReturn();
        // 获取视图
        ModelAndView modelAndView = mvcResult.getModelAndView();
        System.out.println(modelAndView);
    }

    @Test
    public void map() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(
                // 数据的格式请求的url,请求的方法是get
                MockMvcRequestBuilders.get("/map")
                        .contentType(MediaType.APPLICATION_JSON)
        );
        resultActions.andDo(MockMvcResultHandlers.print());
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void hello() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(
                // 数据的格式请求的url,请求的方法是get
                MockMvcRequestBuilders.get("/hello"));
        // 打印调试结果到控制台
        resultActions.andDo(MockMvcResultHandlers.print());
        // 获取返回结果
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}
