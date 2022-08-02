package com.test.sharding.jdbc;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

/**
 * 水平分库分表
 */
public class ShardingJDBCTest {

    public static void main(String[] args) throws SQLException {
//        ShardingJDBCTest.insert();
        ShardingJDBCTest.select();
    }



    public static void insert() throws SQLException {
        // 获取数据源
        DataSource dataSource = getDataSource();

        String insertSql1 = "INSERT INTO t_order VALUES(1000, 10, 'init');";
        String insertSql2 = "INSERT INTO t_order VALUES(1001, 10, 'init');";
        String insertSql3 = "INSERT INTO t_order VALUES(1100, 11, 'init');";
        String insertSql4 = "INSERT INTO t_order VALUES(1101, 11, 'init');";

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(insertSql3)) {
            int num = ps.executeUpdate();
            System.out.println("num = " + num);
        }

    }

    public static void select() throws SQLException {
        // 获取数据源
        DataSource dataSource = getDataSource();

//        String sql = "select * from t_order order";
        String sql = "select * from t_order order by user_id desc";
//        String sql = "select * from t_order order by order_id desc limit 2";

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    int order_id = rs.getInt(1);
                    int user_id = rs.getInt(2);
                    String status = rs.getString(3);
                    System.out.println(order_id + "\t" + user_id + "\t" + status);
                }
            }
        }
    }

    public static DataSource getDataSource() throws SQLException {
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        // 配置第 1 个数据源
        HikariDataSource dataSource1 = new HikariDataSource();
        dataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource1.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test-shardingjdbc1?allowPublicKeyRetrieval=true&useSSL=true");
        dataSource1.setUsername("hd");
        dataSource1.setPassword("123456");
        dataSourceMap.put("ds0", dataSource1);

        // 配置第 2 个数据源
        HikariDataSource dataSource2 = new HikariDataSource();
        dataSource2.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource2.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test-shardingjdbc2?allowPublicKeyRetrieval=true&useSSL=true");
        dataSource2.setUsername("hd");
        dataSource2.setPassword("123456");
        dataSourceMap.put("ds1", dataSource2);

        // 配置 t_order 表规则
        ShardingTableRuleConfiguration orderTableRuleConfig = new ShardingTableRuleConfiguration("t_order", "ds${0..1}.t_order_${0..1}");

        // 配置分库策略
        orderTableRuleConfig.setDatabaseShardingStrategy(new StandardShardingStrategyConfiguration("user_id", "dbShardingAlgorithm"));

        // 配置分表策略
        orderTableRuleConfig.setTableShardingStrategy(new StandardShardingStrategyConfiguration("order_id", "tableShardingAlgorithm"));

        // 省略配置 t_order_item 表规则...
        // ...
//        ShardingTableRuleConfiguration itemTableRuleConfig = new ShardingTableRuleConfiguration("t_order_item", "ds${0..1}.t_order_item_${0..1}");
//        itemTableRuleConfig.setDatabaseShardingStrategy(new StandardShardingStrategyConfiguration("user_id", "dbShardingAlgorithm"));
//        itemTableRuleConfig.setTableShardingStrategy(new StandardShardingStrategyConfiguration("order_id", "tableShardingAlgorithm"));


        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTables().add(orderTableRuleConfig);

        // 配置分库算法
        Properties dbShardingAlgorithmrProps = new Properties();
        dbShardingAlgorithmrProps.setProperty("algorithm-expression", "ds${user_id % 2}");
        shardingRuleConfig.getShardingAlgorithms().put("dbShardingAlgorithm",
                new ShardingSphereAlgorithmConfiguration("INLINE", dbShardingAlgorithmrProps));

        // 配置分表算法
        Properties tableShardingAlgorithmrProps = new Properties();
        tableShardingAlgorithmrProps.setProperty("algorithm-expression", "t_order${order_id % 2}");
        shardingRuleConfig.getShardingAlgorithms().put("tableShardingAlgorithm",
                new ShardingSphereAlgorithmConfiguration("INLINE", tableShardingAlgorithmrProps));

        // 创建 ShardingSphereDataSource
        DataSource dataSource = ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, Collections.singleton(shardingRuleConfig), new Properties());

        return dataSource;
    }
}
