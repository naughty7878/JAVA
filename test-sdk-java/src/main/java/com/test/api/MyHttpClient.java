package com.test.api;

import com.test.api.bean.MyHttpRequest;
import com.test.api.bean.MyHttpResponse;

public class MyHttpClient {

    private static MyHttpClient myHttpClient;

    /**
     * @return  Instance
     */
    public static MyHttpClient getInstance() {
        synchronized (MyHttpClient.class) {
            if(myHttpClient == null) {
                myHttpClient = new MyHttpClient();
            }
        }
        return myHttpClient;
    }

    public MyHttpResponse execute(String url){
        MyHttpRequest myHttpRequest = new MyHttpRequest(url);
        return myHttpRequest.execute();
    }

}
