package com.test.es.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.es.entity.Employee;
import com.test.es.service.EmployeeFullTextService;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.logging.log4j.util.Strings;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import sun.net.www.protocol.http.AuthenticationHeader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeFullTextServiceImpl implements EmployeeFullTextService {

    private RestHighLevelClient restHighLevelClient;

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String INDEX_EMPLOYEE = "employee";

    public EmployeeFullTextServiceImpl() {
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
    public void add(Employee employee) throws IOException {
        //1.	构建IndexRequest对象，用来描述ES发起请求的数据。
        IndexRequest indexRequest = new IndexRequest(INDEX_EMPLOYEE);

        //2.	设置文档ID。
        indexRequest.id(employee.getId() + "");

        //3.	使用FastJSON将实体类对象转换为JSON。
        String json = objectMapper.writeValueAsString(employee);

        //4.	使用IndexRequest.source方法设置文档数据，并设置请求的数据为JSON格式。
        indexRequest.source(json, XContentType.JSON);

        //5.	使用ES High level client调用index方法发起请求，将一个文档添加到索引中。
        restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }

    @Override
    public Employee findById(long id) throws IOException {
        // 1.	构建GetRequest请求。
        GetRequest getRequest = new GetRequest(INDEX_EMPLOYEE, id + "");

        // 2.	使用RestHighLevelClient.get发送GetRequest请求，并获取到ES服务器的响应。
        GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);

        // 3.	将ES响应的数据转换为JSON字符串
        String json = getResponse.getSourceAsString();

        // 4.	并使用FastJSON将JSON字符串转换为JobDetail类对象
        Employee employee = objectMapper.readValue(json, Employee.class);

        // 5.	记得：单独设置ID
//        employee.setId(id);

        return employee;
    }

    @Override
    public void update(Employee employee) throws IOException {
        // 1.	判断对应ID的文档是否存在
        // a)	构建GetRequest
        GetRequest getRequest = new GetRequest(INDEX_EMPLOYEE, employee.getId() + "");

        // b)	执行client的exists方法，发起请求，判断是否存在
        boolean exists = restHighLevelClient.exists(getRequest, RequestOptions.DEFAULT);

        if(exists) {
            // 2.	构建UpdateRequest请求
            UpdateRequest updateRequest = new UpdateRequest(INDEX_EMPLOYEE, employee.getId() + "");

            // 3.	设置UpdateRequest的文档，并配置为JSON格式
            updateRequest.doc(objectMapper.writeValueAsString(employee), XContentType.JSON);

            // 4.	执行client发起update请求
            restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        }
    }



    @Override
    public void deleteById(long id) throws IOException {
        // 1.	构建delete请求
        DeleteRequest deleteRequest = new DeleteRequest(INDEX_EMPLOYEE, id + "");

        // 2.	使用RestHighLevelClient执行delete请求
        restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
    }

    @Override
    public void addBatch(Employee... employees) throws IOException {
        // 1.	构建bulkRequest请求
        BulkRequest bulkRequest = new BulkRequest();

        // 2. 添加对象
        for (Employee employee : employees) {
            bulkRequest.add(new IndexRequest(INDEX_EMPLOYEE).id(employee.getId() + "").source(
                    objectMapper.writeValueAsString(employee), XContentType.JSON
            ));
        }

        // 3.	使用RestHighLevelClient执行delete请求
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println("bulk = " + objectMapper.writeValueAsString(bulk));
    }

    @Override
    public void deleteBatch(Employee... employees) throws IOException {
        // 1.	构建bulkRequest请求
        BulkRequest bulkRequest = new BulkRequest();

        // 2. 添加删除对象ID
        for (Employee employee : employees) {
            bulkRequest.add(new DeleteRequest(INDEX_EMPLOYEE).id(employee.getId() + ""));
        }

        // 3.	使用RestHighLevelClient执行delete请求
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println("bulk = " + objectMapper.writeValueAsString(bulk));
    }

    @Override
    public List<Employee> searchByKeywords(String keywords) throws IOException {
        // 1.构建SearchRequest检索请求
        // 专门用来进行全文检索、关键字检索的API
        SearchRequest searchRequest = new SearchRequest(INDEX_EMPLOYEE);

        // 2.创建一个SearchSourceBuilder专门用于构建查询条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        // 3.使用QueryBuilders.multiMatchQuery构建一个查询条件（搜索title、jd），并配置到SearchSourceBuilder
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(keywords, "name", "job");
        
        // 将查询条件设置到查询请求构建器中
        searchSourceBuilder.query(multiMatchQueryBuilder);
        System.out.println("searchSourceBuilder = " + searchSourceBuilder);

        // 4.调用SearchRequest.source将查询条件设置到检索请求
        searchRequest.source(searchSourceBuilder);
        System.out.println("searchRequest = " + searchRequest);

        // 5.执行RestHighLevelClient.search发起请求
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] hitArray = searchResponse.getHits().getHits();

        // 6.遍历结果
        ArrayList<Employee> employeeArrayList = new ArrayList<>();

        for (SearchHit documentFields : hitArray) {
            // 1)获取命中的结果
            String json = documentFields.getSourceAsString();

            // 2)将JSON字符串转换为对象
            Employee employee = objectMapper.readValue(json, Employee.class);

            // 3)使用SearchHit.getId设置文档ID
            employee.setId(Long.parseLong(documentFields.getId()));

            employeeArrayList.add(employee);
        }

        return employeeArrayList;
    }

    @Override
    public Map<String, Object> searchByPage(String keywords, int pageNum, int pageSize) throws IOException {
        // 1.构建SearchRequest检索请求
        // 专门用来进行全文检索、关键字检索的API
        SearchRequest searchRequest = new SearchRequest(INDEX_EMPLOYEE);

        // 2.创建一个SearchSourceBuilder专门用于构建查询条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        // 3.使用QueryBuilders.multiMatchQuery构建一个查询条件（搜索title、jd），并配置到SearchSourceBuilder
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(keywords, "name", "job");

        // 将查询条件设置到查询请求构建器中
        searchSourceBuilder.query(multiMatchQueryBuilder);

        // 每页显示多少条
        searchSourceBuilder.size(pageSize);
        // 设置从第几条开始查询
        searchSourceBuilder.from((pageNum - 1) * pageSize);

        // 4.调用SearchRequest.source将查询条件设置到检索请求
        searchRequest.source(searchSourceBuilder);

        // 5.执行RestHighLevelClient.search发起请求
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] hitArray = searchResponse.getHits().getHits();

        // 6.遍历结果
        ArrayList<Employee> employeeArrayList = new ArrayList<>();

        for (SearchHit documentFields : hitArray) {
            // 1)获取命中的结果
            String json = documentFields.getSourceAsString();

            // 2)将JSON字符串转换为对象
            Employee employee = objectMapper.readValue(json, Employee.class);

            // 3)使用SearchHit.getId设置文档ID
            employee.setId(Long.parseLong(documentFields.getId()));

            employeeArrayList.add(employee);
        }

        // 8.	将结果封装到Map结构中（带有分页信息）
        // a)	total -> 使用SearchHits.getTotalHits().value获取到所有的记录数
        // b)	content -> 当前分页中的数据
        long totalNum = searchResponse.getHits().getTotalHits().value;
        HashMap hashMap = new HashMap();
        hashMap.put("total", totalNum);
        hashMap.put("content", employeeArrayList);


        return hashMap;
    }

    @Override
    public Map<String, Object> searchByScrollPage(String keywords, String scrollId, int pageSize) throws IOException {
        SearchResponse searchResponse = null;

        if(scrollId == null) {
            // 1.构建SearchRequest检索请求
            // 专门用来进行全文检索、关键字检索的API
            SearchRequest searchRequest = new SearchRequest(INDEX_EMPLOYEE);

            // 2.创建一个SearchSourceBuilder专门用于构建查询条件
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

            // 3.使用QueryBuilders.multiMatchQuery构建一个查询条件（搜索title、jd），并配置到SearchSourceBuilder
            MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(keywords, "name", "job");

            // 将查询条件设置到查询请求构建器中
            searchSourceBuilder.query(multiMatchQueryBuilder);

            // 设置高亮
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("name");
            highlightBuilder.field("job");
            highlightBuilder.preTags("<font color='red'>");
            highlightBuilder.postTags("</font>");

            // 给请求设置高亮
            searchSourceBuilder.highlighter(highlightBuilder);

            // 每页显示多少条
            searchSourceBuilder.size(pageSize);

            // 4.调用SearchRequest.source将查询条件设置到检索请求
            searchRequest.source(searchSourceBuilder);
            System.out.println("searchSourceBuilder = " + searchSourceBuilder);

            //--------------------------
            // 设置scroll查询
            //--------------------------
            searchRequest.scroll(TimeValue.timeValueMinutes(5));

            // 5.执行RestHighLevelClient.search发起请求
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        }
        // 第二次查询的时候，直接通过scroll id查询数据
        else {
            SearchScrollRequest searchScrollRequest = new SearchScrollRequest(scrollId);
            searchScrollRequest.scroll(TimeValue.timeValueMinutes(5));

            // 使用RestHighLevelClient发送scroll请求
            searchResponse = restHighLevelClient.scroll(searchScrollRequest, RequestOptions.DEFAULT);
        }

        //--------------------------
        // 迭代ES响应的数据
        //--------------------------

        SearchHit[] hitArray = searchResponse.getHits().getHits();

        // 6.遍历结果
        ArrayList<Employee> employeeArrayList = new ArrayList<>();

        for (SearchHit documentFields : hitArray) {
            // 1)获取命中的结果
            String json = documentFields.getSourceAsString();

            // 2)将JSON字符串转换为对象
            Employee employee = objectMapper.readValue(json, Employee.class);

            // 3)使用SearchHit.getId设置文档ID
            employee.setId(Long.parseLong(documentFields.getId()));

            employeeArrayList.add(employee);

            // 设置高亮的一些文本到实体类中
            // 封装了高亮
            Map<String, HighlightField> highlightFieldMap = documentFields.getHighlightFields();
            HighlightField nameHL = highlightFieldMap.get("name");
            HighlightField jobHL = highlightFieldMap.get("job");

            if(nameHL != null) {
                // 获取指定字段的高亮片段
                Text[] fragments = nameHL.getFragments();
                // 将这些高亮片段拼接成一个完整的高亮字段
                StringBuilder builder = new StringBuilder();
                for(Text text : fragments) {
                    builder.append(text);
                }
                // 设置到实体类中
                employee.setName(builder.toString());
            }

            if(jobHL != null) {
                // 获取指定字段的高亮片段
                Text[] fragments = jobHL.getFragments();
                // 将这些高亮片段拼接成一个完整的高亮字段
                StringBuilder builder = new StringBuilder();
                for(Text text : fragments) {
                    builder.append(text);
                }
                // 设置到实体类中
                employee.setJob(builder.toString());
            }
        }

        // 8.	将结果封装到Map结构中（带有分页信息）
        // a)	total -> 使用SearchHits.getTotalHits().value获取到所有的记录数
        // b)	content -> 当前分页中的数据
        long totalNum = searchResponse.getHits().getTotalHits().value;
        HashMap hashMap = new HashMap();
        hashMap.put("scroll_id", searchResponse.getScrollId());
        hashMap.put("content", employeeArrayList);

        return hashMap;
    }

    @Override
    public void close() throws IOException {
        restHighLevelClient.close();
    }
}
