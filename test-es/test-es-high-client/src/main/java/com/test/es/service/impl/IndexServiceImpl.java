package com.test.es.service.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.test.es.service.IndexService;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

import java.io.IOException;

public class IndexServiceImpl implements IndexService {

    private RestHighLevelClient restHighLevelClient;

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String INDEX_TEST_INDEX = "test-index";

    {
        //反序列化的时候如果多了其他属性,不抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //如果是空对象的时候,不抛异常
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        //属性为null的转换
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

    }

    public IndexServiceImpl() {
        // 建立与ES的连接
        // 1. 使用RestHighLevelClient构建客户端连接。
        // 2. 基于RestClient.builder方法来构建RestClientBuilder
        // 3. 用HttpHost来添加ES的节点
        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("es.naughty7878.top"))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "god123456"));
                        return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    }
                });
       /* RestClientBuilder restClientBuilder = RestClient.builder(
                new HttpHost("192.168.21.130", 9200, "http"));*/
        restHighLevelClient = new RestHighLevelClient(restClientBuilder);
    }

    @Override
    public void add() throws IOException {
        //1.	构建CreateIndexRequest对象，用来描述ES发起请求的数据。
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(INDEX_TEST_INDEX);

        //2.	使用ES High level client调用createIndexRequest方法发起请求，创建索引中。
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);

        String response = objectMapper.writeValueAsString(createIndexResponse);
        System.out.println("response = " + response);
        
    }


    @Override
    public void get() throws IOException {
        //1.	构建CreateIndexRequest对象，用来描述ES发起请求的数据。
        GetIndexRequest getIndexRequest = new GetIndexRequest(INDEX_TEST_INDEX);

        //2.	使用ES High level client调用createIndexRequest方法发起请求，创建索引中。
        GetIndexResponse getIndexResponse = restHighLevelClient.indices().get(getIndexRequest, RequestOptions.DEFAULT);

        String response = objectMapper.writeValueAsString( getIndexResponse.getIndices());
        System.out.println("response = " + response);

    }

    @Override
    public void delete() throws IOException {
        //1.	构建CreateIndexRequest对象，用来描述ES发起请求的数据。
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(INDEX_TEST_INDEX);

        //2.	使用ES High level client调用createIndexRequest方法发起请求，创建索引中。
        AcknowledgedResponse acknowledgedResponse = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);

        String response = objectMapper.writeValueAsString(acknowledgedResponse);
        System.out.println("response = " + response);

    }

    @Override
    public void close() throws IOException {
        restHighLevelClient.close();
    }
}
