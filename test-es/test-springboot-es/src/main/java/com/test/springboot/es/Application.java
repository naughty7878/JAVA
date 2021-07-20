package com.test.springboot.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot默认支持两种技术来与ES交互
 * 1、Jest（默认不生效，需要导入jest工具包）
 * 2、SpringBoot ElasticSearch（ES版本可能不合适，需要相应版本）
 *      版本适配说明：https://docs.spring.io/spring-data/elasticsearch/docs/4.0.0.M4/reference/html/#reference
 *      1)、Client 节点信息 clusterNodes；clusterName
 *      2)、ElasticsearchTemplate 操作es
 *      3)、编写一个ElasticsearchRespository的子接口来操ES；
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
