package com.test.springboot.es;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.*;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestHighRestClient {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private RestHighLevelClient restHighClient;

    @Before
    public void init() {

        RestClientBuilder restClientBuilder = RestClient.builder(
                new HttpHost("127.0.0.1", 19201, "http"),
                new HttpHost("127.0.0.1", 19202, "http"),
                new HttpHost("127.0.0.1", 19203, "http"));
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

    // 新增文档，同步操作
    @Test
    public void testCreate() throws Exception {
        Map<String, Object> data = new HashMap<>();
        data.put("id", "2002");
        data.put("title", "南京西路 拎包入住 一室一厅");
        data.put("price", "4500");
        IndexRequest indexRequest = new IndexRequest("house", "_doc").source(data);
        IndexResponse indexResponse = restHighClient.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println("id->" + indexResponse.getId());
        System.out.println("index->" + indexResponse.getIndex());
        System.out.println("type->" + indexResponse.getType());
        System.out.println("version->" + indexResponse.getVersion());
        System.out.println("result->" + indexResponse.getResult());
        System.out.println("shardInfo->" + indexResponse.getShardInfo());
    }

    //  新增文档，异步操作
//    @Test
//    public void testCreateAsync() throws Exception {
//        Map<String, Object> data = new HashMap<>();
//        data.put("id", "2003");
//        data.put("title", "南京东路 最新房源 二室一厅");
//        data.put("price", "5500");
//        IndexRequest indexRequest = new IndexRequest("house", "_doc").source(data);
//        restHighClient.indexAsync(indexRequest, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
//            @Override
//            public void onResponse(IndexResponse indexResponse) {
//                System.out.println("id->" + indexResponse.getId());
//                System.out.println("index->" + indexResponse.getIndex());
//                System.out.println("type->" + indexResponse.getType());
//                System.out.println("version->" + indexResponse.getVersion());
//                System.out.println("result->" + indexResponse.getResult());
//                System.out.println("shardInfo->" + indexResponse.getShardInfo());
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//                System.out.println(e);
//            }
//        });
//        System.out.println("ok");
//        Thread.sleep(20000);
//    }

    // 测试查询
    @Test
    public void testQuery() throws Exception {
        GetRequest getRequest = new GetRequest("house", "_doc",
                "jLI5snIB-J-F9-0D8j7h");
        // 指定返回的字段
        String[] includes = new String[]{"title", "id"};
        String[] excludes = Strings.EMPTY_ARRAY;
        FetchSourceContext fetchSourceContext =
                new FetchSourceContext(true, includes, excludes);
        getRequest.fetchSourceContext(fetchSourceContext);
        GetResponse response = restHighClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println("数据 -> " + response.getSource());
    }


    // 判断是否存在
    @Test
    public void testExists() throws Exception {
        GetRequest getRequest = new GetRequest("house", "_doc", "jLI5snIB-J-F9-0D8j7h"); // 不返回的字段
        // 不返回的字段
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        boolean exists = restHighClient.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println("exists -> " + exists);
    }

    // 删除数据
    @Test
    public void testDelete() throws Exception {

        DeleteRequest deleteRequest = new DeleteRequest("house", "_doc",
                "jLI5snIB-J-F9-0D8j7h");
        DeleteResponse response = restHighClient.delete(deleteRequest,
                RequestOptions.DEFAULT);
        System.out.println(response.status());// OK or NOT_FOUND
    }

    // 更新数据
    @Test
    public void testUpdate() throws Exception {
        UpdateRequest updateRequest = new UpdateRequest("house", "_doc",
                "i7I4snIB-J-F9-0D3D6H");
        Map<String, Object> data = new HashMap<>();
        data.put("title", "张江高科2");
        data.put("price", "5000");
        updateRequest.doc(data);
        UpdateResponse response = restHighClient.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println("version -> " + response.getVersion());
    }


    // 搜索数据
    @Test
    public void testSearch() throws Exception {
        SearchRequest searchRequest = new SearchRequest("house");
        searchRequest.types("_doc");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchQuery("title", "拎包入住"));
        sourceBuilder.from(0);
        sourceBuilder.size(5);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);
        SearchResponse search = restHighClient.search(searchRequest, RequestOptions.DEFAULT);
//        System.out.println("搜索到 " + search.getHits().getTotalHits() + " 条数据.");
        SearchHits hits = search.getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
    }
}
