package com.test.sharding.jdbc;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.dbdiscovery.mgr.MGRDatabaseDiscoveryType;
import org.apache.shardingsphere.dbdiscovery.spi.DatabaseDiscoveryType;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.DatabaseAccessConfiguration;
import org.apache.shardingsphere.infra.config.RuleConfiguration;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.apache.shardingsphere.readwritesplitting.algorithm.RoundRobinReplicaLoadBalanceAlgorithm;
import org.apache.shardingsphere.readwritesplitting.api.ReadwriteSplittingRuleConfiguration;
import org.apache.shardingsphere.readwritesplitting.api.rule.ReadwriteSplittingDataSourceRuleConfiguration;
import org.apache.shardingsphere.readwritesplitting.rule.ReadwriteSplittingDataSourceRule;
import org.apache.shardingsphere.readwritesplitting.spi.ReplicaLoadBalanceAlgorithm;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.sharding.route.engine.type.broadcast.ShardingDatabaseBroadcastRoutingEngine;
import org.apache.shardingsphere.sql.parser.autogen.MySQLStatementParser;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * 主从配置
 */
public class ShardingMasterSlaveTest {

    public static void main(String[] args) throws SQLException {
        ShardingMasterSlaveTest.insert();
        ShardingMasterSlaveTest.select();
    }

    public static void insert() throws SQLException {
        // 获取数据源
        DataSource dataSource = getDataSource();

        String insertSql1 = "INSERT INTO user VALUES(1, '小白');";
        String insertSql2 = "INSERT INTO user VALUES(2, '小黑');";
        String insertSql3 = "INSERT INTO user VALUES(3, '小黄');";
        String insertSql4 = "INSERT INTO user VALUES(4, '小蓝');";

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(insertSql2)) {
            int num = ps.executeUpdate();
            System.out.println("num = " + num);
        }

    }


    public static void select() throws SQLException {
        // 获取数据源
        DataSource dataSource = getDataSource();

        String sql = "select * from user";

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    int order_id = rs.getInt(1);
                    String name = rs.getString(2);
                    System.out.println(order_id + "\t" + name);
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
        dataSource1.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test_master_slave?allowPublicKeyRetrieval=true&useSSL=true");
        dataSource1.setUsername("hd");
        dataSource1.setPassword("123456");
        dataSourceMap.put("ds0", dataSource1);

        // 配置第 2 个数据源
        HikariDataSource dataSource2 = new HikariDataSource();
        dataSource2.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource2.setJdbcUrl("jdbc:mysql://120.78.189.168:3306/test_master_slave?allowPublicKeyRetrieval=true&useSSL=true");
        dataSource2.setUsername("hd");
        dataSource2.setPassword("123456");
        dataSourceMap.put("ds1", dataSource2);


        // 读写数据源配置
        ReadwriteSplittingDataSourceRuleConfiguration dataSourceRuleConfiguration = new ReadwriteSplittingDataSourceRuleConfiguration(
                "master_slave_db", null, "ds0", Arrays.asList("ds1"), "ROUND_ROBIN");

        // 读写分离配置
        ReadwriteSplittingRuleConfiguration readwriteSplittingRuleConfiguration = new ReadwriteSplittingRuleConfiguration(
                Collections.singleton(dataSourceRuleConfiguration), new HashMap<>());

        // 创建 ShardingSphereDataSource
        DataSource dataSource = ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, Collections.singleton(readwriteSplittingRuleConfiguration), new Properties());

        return dataSource;
    }
}
