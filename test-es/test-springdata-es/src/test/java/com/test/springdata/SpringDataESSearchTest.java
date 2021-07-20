package com.test.springdata;

import com.test.springdata.dao.ProductDao;
import com.test.springdata.entity.Product;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.search.MatchQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataESSearchTest {
    @Autowired
    private ProductDao productDao;
    /**
     * term查询
     * search(termQueryBuilder) 调用搜索方法，参数查询构建器对象 */
    @Test
    public void termQuery(){
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", " 小米");
        Iterable<Product> products = productDao.search(termQueryBuilder);
        for (Product product : products) {
            System.out.println(product);
        }
    }
    /**
     * term查询加分页 */
    @Test
    public void termQueryByPage(){
        int currentPage= 0 ;
        int pageSize = 5;
        //设置查询分页
        PageRequest pageRequest = PageRequest.of(currentPage, pageSize);
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "小米");
        Iterable<Product> products = productDao.search(matchQueryBuilder,pageRequest);
        for (Product product : products) {
            System.out.println(product);
        } }
}