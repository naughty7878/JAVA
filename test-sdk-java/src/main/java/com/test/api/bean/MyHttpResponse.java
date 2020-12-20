package com.test.api.bean;

import cn.hutool.http.HttpResponse;

public class MyHttpResponse{

    HttpResponse httpResponse;

    public HttpResponse getHttpResponse() {
        return httpResponse;
    }

    public void setHttpResponse(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;

    }

    public int getStatus() {
        if(httpResponse == null) {
            throw new NullPointerException("httpResponse is empty !");
        }
        return httpResponse.getStatus();
    }

    public String body(){
        if(httpResponse == null) {
            throw new NullPointerException("httpResponse is empty !");
        }
        return httpResponse.body();
    }
}
