package com.test.springdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestHighRestClient {

    @Autowired
    ApplicationContext context;
    
    @Autowired
    RestHighLevelClient restHighLevelClient;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void contextLoads() {
        System.out.println("restHighLevelClient = " + restHighLevelClient);
    }

    @Test
    public void test2() throws IOException {
        //1.	构建CreateIndexRequest对象，用来描述ES发起请求的数据。
        GetIndexRequest getIndexRequest = new GetIndexRequest("employee");

        //2.	使用ES High level client调用createIndexRequest方法发起请求，创建索引中。
        GetIndexResponse getIndexResponse = restHighLevelClient.indices().get(getIndexRequest, RequestOptions.DEFAULT);

        String response = objectMapper.writeValueAsString( getIndexResponse.getIndices());
        System.out.println("response = " + objectMapper.writeValueAsString(getIndexResponse.getIndices()));
    }
}

