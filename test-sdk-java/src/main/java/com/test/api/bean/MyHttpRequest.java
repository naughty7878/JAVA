package com.test.api.bean;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;

public class MyHttpRequest {

    private HttpRequest httpRequest;

    public MyHttpRequest(String url){
        httpRequest = HttpRequest.get(url);
        // 可以封装其他逻辑
    }

    public MyHttpResponse execute(){
        if(httpRequest == null) {
            throw new NullPointerException("httpRequest is empty !");
        }
        HttpResponse httpResponse = httpRequest.execute();
        MyHttpResponse myHttpResponse = new MyHttpResponse();
        myHttpResponse.setHttpResponse(httpResponse);
        return myHttpResponse;
    }

    public static void main(String[] args) {
        MyHttpRequest myHttpRequest = new MyHttpRequest("https://www.sina.com");
        MyHttpResponse myHttpResponse = myHttpRequest.execute();
        int status = myHttpResponse.getStatus();
        if(status == 200) {
            System.out.println(myHttpResponse.body());
        }else {
            System.out.println("status === " + status );
            System.out.println("请求失败！");
        }
    }
}
