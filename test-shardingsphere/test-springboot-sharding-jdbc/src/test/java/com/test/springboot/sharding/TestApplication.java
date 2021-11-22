package com.test.springboot.sharding;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplication {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void context() throws SQLException {
        System.out.println("========");
        System.out.println("dataSource ====" + dataSource);

        Connection connection = dataSource.getConnection();
        System.out.println("connecttion ====" + connection);

        Connection connection1 = jdbcTemplate.getDataSource().getConnection();
        System.out.println("connection1 = " + connection1);

        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from t_config");
        System.out.println(maps);
//        jdbcTemplate.execute("insert into t_config values(5, 'finish')");
    }

    @Test
    public void test02() throws SQLException {

        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from t_order where user_id = 10 and order_id = 1000");
        System.out.println(maps);
    }

    @Test
    public void test03() throws SQLException {
        System.out.println("\"668421745522245632\".length() = " + "668421745522245632".length());
        jdbcTemplate.execute("insert into t_order(`user_id`, `status`) values(10, 'init')");

    }


    @Test
    public void test04() throws SQLException {
        String sql = "SELECT i.* FROM  t_order o, t_order_item i WHERE o.order_id = i.order_id AND o.order_id = 100 AND o.user_id = 10";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        System.out.println("maps = " + maps);

    }


        @Test
        public void test05() throws SQLException {
    //        jdbcTemplate.update("insert into user(`id`, `text`) values(3, 'abc')", new Object[]{"abc"});
    //        jdbcTemplate.update("insert into user(`id`, password, pwd, pwd2) values(?, ?, ?, ?)", new Object[]{4, "abc", "abc", "abc"});
            jdbcTemplate.update("insert into user(`id`, password, pwd, pwd2) values(?, ?, ?, ?)", new Object[]{5, "abc", "abc", "abc"});

        }


    @Test
    public void test06() throws SQLException {

        String sql = "SELECT * FROM  user where id = 3";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        System.out.println("maps = " + maps);

    }
}