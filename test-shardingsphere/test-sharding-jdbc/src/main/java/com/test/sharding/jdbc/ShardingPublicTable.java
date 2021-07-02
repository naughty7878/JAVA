package com.test.sharding.jdbc;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.sharding.algorithm.keygen.SnowflakeKeyGenerateAlgorithm;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.keygen.KeyGenerateStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.NoneShardingStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * 公共表
 */
public class ShardingPublicTable {

    public static void main(String[] args) throws SQLException {
//        ShardingPublicTable.test1();
        ShardingPublicTable.test2();
    }



    public static void test1() throws SQLException {
        // 获取数据源
        DataSource dataSource = getDataSource();

        String insertSql1 = "INSERT INTO t_config VALUES(1, 'init');";
        String insertSql2 = "INSERT INTO t_config VALUES(2, 'start');";
        String insertSql3 = "INSERT INTO t_config VALUES(3, 'run');";
        String insertSql4 = "INSERT INTO t_config VALUES(4, 'end');";

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(insertSql4)) {
            int num = ps.executeUpdate();
            System.out.println("num = " + num);
        }

    }

    public static void test2() throws SQLException {
        // 获取数据源
        DataSource dataSource = getDataSource();

//        String sql = "select * from t_order order";
        String sql = "select * from t_config";
//        String sql = "select * from t_order order by order_id desc limit 2";

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    int id = rs.getInt(1);
                    String status = rs.getString(2);
                    System.out.println(id + "\t" + status);
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
        dataSource1.setJdbcUrl("jdbc:mysql://47.106.74.177:13306/test-shardingjdbc1?allowPublicKeyRetrieval=true&useSSL=true");
        dataSource1.setUsername("hd");
        dataSource1.setPassword("hd123456");
        dataSourceMap.put("ds0", dataSource1);

        // 配置第 2 个数据源
        HikariDataSource dataSource2 = new HikariDataSource();
        dataSource2.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource2.setJdbcUrl("jdbc:mysql://47.106.74.177:13306/test-shardingjdbc2?allowPublicKeyRetrieval=true&useSSL=true");
        dataSource2.setUsername("hd");
        dataSource2.setPassword("hd123456");
        dataSourceMap.put("ds1", dataSource2);

        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        // 配置公共表
        shardingRuleConfig.setBroadcastTables(Arrays.asList("t_config"));

        // 创建 ShardingSphereDataSource
        DataSource dataSource = ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, Collections.singleton(shardingRuleConfig), new Properties());

        return dataSource;
    }
}
