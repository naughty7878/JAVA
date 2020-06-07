package com.test.sdk;

import com.test.api.MyHttpClient;
import com.test.api.bean.MyHttpResponse;

public class MyMain {
    public static void main(String[] args) {
        // 配置
        String url = "https://www.sina.com";
        // 获取SDK中客户端
        MyHttpClient myHttpClient = MyHttpClient.getInstance();
        // 执行业务
        MyHttpResponse myHttpResponse = myHttpClient.execute(url);
        int status = myHttpResponse.getStatus();
        if(status == 200) {
            System.out.println("请求成功");
            System.out.println(myHttpResponse.body());
        }else {
            System.out.println("status === " + status );
            System.out.println("请求失败！");
        }
    }
}
