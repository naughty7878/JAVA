package com.test.jooq;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author h__d
 * @description TODO
 * @date 2021/10/23
 */
public class TestFetch {

    @Test
    public void test01() {
        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8";
        String jdbcUsername = "admin";
        String jdbcPassword = "123456";

        // 获取 JDBC 链接
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            // 获取 jOOQ 执行器
            DSLContext dslContext = DSL.using(connection, SQLDialect.MYSQL);

//            Result<Record> records = dslContext.select().from(User.USER).fetch();
//            System.out.println("records = " + records);

//            Set<?> objects = dslContext.select().from(User.USER).fetchSet(1);
//            System.out.println("objects = " + objects);

//            Record[] array = dslContext.select().from(User.USER).fetchArray();
//            Arrays.stream(array).forEach(a -> System.out.println("a = " + a));

//            Record record = dslContext.select().from(User.USER).where(User.USER.ID.equal(111)).limit(1).fetchOne();
//            System.out.println("record = " + record);

//            Record record = dslContext.select().from(User.USER).fetchAny();
//            System.out.println("record = " + record);

//            Record record = dslContext.select().from(User.USER).where(User.USER.ID.equal(1)).fetchSingle();
//            System.out.println("record = " + record);

//            Map<Integer, com.test.jooq.codegen.tables.pojos.User> userMap = dslContext.select().from(User.USER).fetchMap(User.USER.ID, com.test.jooq.codegen.tables.pojos.User.class);
//            userMap.forEach((k, v) -> {
//                System.out.println("k = " + k);
//                System.out.println("v = " + v);
//            });

//            Map<String, List<com.test.jooq.codegen.tables.pojos.User>> stringListMap = dslContext.select().from(User.USER).fetchGroups(User.USER.NAME, com.test.jooq.codegen.tables.pojos.User.class);
//            stringListMap.forEach((k, v) -> {
//                System.out.println("k = " + k);
//                System.out.println("v = " + v);
//            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
