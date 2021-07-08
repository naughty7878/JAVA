package com.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.client.*;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class TestHighRestClient {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private RestHighLevelClient restHighClient;

    @Before
    public void init() {

        RestClientBuilder restClientBuilder = RestClient.builder(
//                new HttpHost("127.0.0.1", 19201, "http"),
//                new HttpHost("127.0.0.1", 19202, "http"),
                new HttpHost("es.naughty7878.top", 80, "http"));
        restClientBuilder.setFailureListener(new RestClient.FailureListener() {
            @Override
            public void onFailure(Node node) {
                System.out.println("出错了 -> " + node);
            }
        });

        this.restHighClient = new RestHighLevelClient(restClientBuilder);
    }

    @After
    public void after() throws IOException {
        restHighClient.close();
    }

    // 判断是否存在
    @Test
    public void testExists() throws Exception {
        GetRequest getRequest = new GetRequest("house", "_doc", "jLI5snIB-J-F9-0D8j7h" ); // 不返回的字段
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        boolean exists = restHighClient.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println("exists -> " + exists);
    }


    // 新增数据
//    @Test
//    public void testCreateData() throws IOException {
//        Request request = new Request("POST", "/house/_doc");
//        Map<String, Object> data = new HashMap<>();
//        data.put("id", "2001");
//        data.put("title", "张江高科");
//        data.put("price", "3500");
//        request.setJsonEntity(MAPPER.writeValueAsString(data));
//        Response response = this.restClient.performRequest(request);
//        System.out.println(response.getStatusLine());
//        System.out.println(EntityUtils.toString(response.getEntity()));
//    }
//
//
//    // 根据id查询数据
//    @Test
//    public void testQueryData() throws IOException {
//        Request request = new Request("GET", "/house/_doc/i7I4snIB-J-F9-0D3D6H");
//        Response response = this.restClient.performRequest(request);
//        System.out.println(response.getStatusLine());
//        System.out.println(EntityUtils.toString(response.getEntity()));
//    }
//
//    // 搜索数据
//    @Test
//    public void testSearchData() throws IOException {
//        Request request = new Request("POST", "/house/_search");
//        String searchJson = "{\"query\": {\"match\": {\"title\": \"拎包入住\"}}}";
//        request.setJsonEntity(searchJson);
//        request.addParameter("pretty", "true");
//        Response response = this.restClient.performRequest(request);
//        System.out.println(response.getStatusLine());
//        System.out.println(EntityUtils.toString(response.getEntity()));
//    }

}
