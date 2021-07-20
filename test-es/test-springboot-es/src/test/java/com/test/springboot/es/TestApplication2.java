//package com.test.springboot.es;
//
//import com.test.springboot.es.bean.Article;
//import com.test.springboot.es.bean.repository.ArticleRepository;
//import io.searchbox.client.JestClient;
//import io.searchbox.core.Index;
//import io.searchbox.core.Search;
//import io.searchbox.core.SearchResult;
//import org.elasticsearch.action.support.IndicesOptions;
//import org.elasticsearch.client.Client;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.core.query.*;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.IOException;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class TestApplication2 {
//
//    @Autowired
//    ElasticsearchTemplate elasticsearchTemplate;
//
//    @Autowired
//    ArticleRepository articleRepository;
//
//
//    @Test
//    public void findByAuthor(){
//        List<Article> articles = articleRepository.findByAuthor("李四");
//        if(articles.size() > 0) {
//            for (Article a : articles){
//                System.out.println(a.toString());
//            }
//        }
//    }
//
//    @Test
//    public void test01(){
//        Article article = new Article();
//        article.setId(2);
//        article.setTitle("慢消息");
//        article.setAuthor("李四");
//        article.setContent("Hello XXXX");
//
//        articleRepository.save(article);
//
//    }
//
//
//    // 给ES索引（保存）一个文档
//    @Test
//    public void save() {
//        Article article = new Article();
//        article.setId(1);
//        article.setTitle("好消息");
//        article.setAuthor("张三");
//        article.setContent("Hello Word");
//
//        IndexQuery indexQuery = new IndexQueryBuilder()
//                .withId(article.getId().toString())
//                .withObject(article)
//                .build();
//
//        // 存入索引，返回文档ID
//        String documentId = elasticsearchTemplate.index(indexQuery);
//        System.out.println(documentId);
//
//    }
//
//    // 测试elasticsearchTemplate搜索
//    @Test
//    public void search() throws IOException {
//
//        String json = "{\n" +
//                "        \"match\" : {\n" +
//                "            \"content\" : \"Hello\"\n" +
//                "        }\n" +
//                "    }";
//
//        StringQuery query = new StringQuery(json);
////        query.addIndices("test");
////        query.addTypes("news");
//
//        List<Article> articles = elasticsearchTemplate.queryForList(query, Article.class);
//        if(articles.size() > 0) {
//            for (Article a : articles){
//                System.out.println(a.toString());
//            }
//        }
//    }
//}
