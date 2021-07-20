package com.test.springboot.es.bean.repository;

import com.test.springboot.es.bean.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ArticleRepository extends ElasticsearchRepository<Article, Integer> {

    List<Article> findByAuthor(String author);

}
