package com.test.jooq;

import com.test.jooq.codegen.tables.User;
import com.test.jooq.codegen.tables.UserMsg;
import com.test.jooq.codegen.tables.records.UserRecord;
import com.test.jooq.pojo.UserMessagePojo;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author h__d
 * @description TODO
 * @date 2021/10/22
 */
public class TestJooQ {

    /**
     * 删除
     */
    @Test
    public void test12() {
        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8";
        String jdbcUsername = "admin";
        String jdbcPassword = "123456";

        // 获取 JDBC 链接
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            // 获取 jOOQ 执行器
            DSLContext dslContext = DSL.using(connection, SQLDialect.MYSQL);
//
            UserRecord record1 = new UserRecord();
            record1.setId(17);
            UserRecord record2 = new UserRecord();
            record2.setId(19);
            dslContext.batchDelete(record1, record2).execute();
//
            List<UserRecord> recordList = new ArrayList<>();
            recordList.add(record1);
            recordList.add(record2);
            dslContext.batchDelete(recordList).execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Delete
     */
    @Test
    public void test11() {
        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8";
        String jdbcUsername = "admin";
        String jdbcPassword = "123456";

        // 获取 JDBC 链接
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            // 获取 jOOQ 执行器
            DSLContext dslContext = DSL.using(connection, SQLDialect.MYSQL);
//
            dslContext.delete(User.USER).where(User.USER.NAME.eq("小兰2")).execute();


            UserRecord record = dslContext.newRecord(User.USER);
            record.setId(17);
            int deleteRows = record.delete();
            // deleteRows = 1
            // SQL: delete from `learn-jooq`.`User.USER` where `learn-jooq`.`User.USER`.`id` = 2

            UserRecord record2 = dslContext.newRecord(User.USER);
            record2.setName("小紫");
            int deleteRows2 = record2.delete();
            // deleteRows == 0
            // SQL: delete from `learn-jooq`.`User.USER` where `learn-jooq`.`User.USER`.`id` is null

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 关联查询
     */
    @Test
    public void test10() {
        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8";
        String jdbcUsername = "admin";
        String jdbcPassword = "123456";

        // 获取 JDBC 链接
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            // 获取 jOOQ 执行器
            DSLContext dslContext = DSL.using(connection, SQLDialect.MYSQL);
//
            Result<Record3<Integer, String, String>> record3Result =
                    dslContext.select(User.USER.ID.as("uid"),
                                    User.USER.NAME,
                                    UserMsg.USER_MSG.MSG)
                            .from(User.USER)
                            .leftJoin(UserMsg.USER_MSG).on(User.USER.ID.eq(UserMsg.USER_MSG.UID))
                            .where(UserMsg.USER_MSG.UID.isNotNull())
                            .fetch();
            List<UserMessagePojo> userMessagePojoList = record3Result.into(UserMessagePojo.class);
            userMessagePojoList.forEach(u -> System.out.println("u = " + u));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 单表查询
     */
    @Test
    public void test9() {
        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8";
        String jdbcUsername = "admin";
        String jdbcPassword = "123456";

        // 获取 JDBC 链接
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            // 获取 jOOQ 执行器
            DSLContext dslContext = DSL.using(connection, SQLDialect.MYSQL);

            // select `learn-jooq`.`User.USER`.`id`, `learn-jooq`.`User.USER`.`username`, `learn-jooq`.`User.USER`.`email`, `learn-jooq`.`User.USER`.`address`, `learn-jooq`.`User.USER`.`create_time`, `learn-jooq`.`User.USER`.`update_time` from `learn-jooq`.`User.USER`
            Result<Record> fetchResult = dslContext.select().from(User.USER).fetch();
            List<UserRecord> result = fetchResult.into(UserRecord.class);

            // select `learn-jooq`.`User.USER`.`id`, `learn-jooq`.`User.USER`.`username`, `learn-jooq`.`User.USER`.`email`, `learn-jooq`.`User.USER`.`address`, `learn-jooq`.`User.USER`.`create_time`, `learn-jooq`.`User.USER`.`update_time` from `learn-jooq`.`User.USER` where `learn-jooq`.`User.USER`.`id` in (1, 2)
            Result<Record> fetchAll = dslContext.select().from(User.USER)
                    .where(User.USER.ID.in(1, 2)).fetch();
            fetchAll.forEach(record -> {
                Integer id = record.getValue(User.USER.ID);
                String name = record.getValue(User.USER.NAME);
                Integer age = record.getValue(User.USER.AGE);
                Timestamp createTime = record.getValue(User.USER.CREATE_TIME);
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 批量更新
     */
    @Test
    public void test8() {
        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8";
        String jdbcUsername = "admin";
        String jdbcPassword = "123456";

        // 获取 JDBC 链接
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            // 获取 jOOQ 执行器
            DSLContext dslContext = DSL.using(connection, SQLDialect.MYSQL);

            UserRecord record1 = new UserRecord();
            record1.setId(1);
            record1.setName("batchUsername-1");
            UserRecord record2 = new UserRecord();
            record2.setId(2);
            record2.setName("batchUsername-2");

            List<UserRecord> userRecordList = new ArrayList<>();
            userRecordList.add(record1);
            userRecordList.add(record2);
            dslContext.batchUpdate(userRecordList).execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Update
     */
    @Test
    public void test07() {
        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8";
        String jdbcUsername = "admin";
        String jdbcPassword = "123456";

        // 获取 JDBC 链接
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            // 获取 jOOQ 执行器
            DSLContext dslContext = DSL.using(connection, SQLDialect.MYSQL);

            dslContext.update(User.USER)
                    .set(User.USER.NAME, "haha")
                    .set(User.USER.AGE, 9)
                    .where(User.USER.ID.eq(1))
                    .execute();



            UserRecord record = dslContext.newRecord(User.USER);
            record.setId(2);
            record.setName("xxl");
            record.setAge(11);
            record.update();
            // 生成SQL:  update `learn-jooq`.`User.USER` set `learn-jooq`.`User.USER`.`id` = 1, `learn-jooq`.`User.USER`.`username` = 'usernameUpdate-2',
            // `learn-jooq`.`User.USER`.`address` = 'record-address-2' where `learn-jooq`.`User.USER`.`id` = 1

            UserRecord record2 = dslContext.newRecord(User.USER);
            record2.setName("xxl2");
            record2.update();
            // 生成SQL: update `learn-jooq`.`User.USER` set `learn-jooq`.`User.USER`.`username` = 'usernameUpdate-noID' where `learn-jooq`.`User.USER`.`id` is null

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 主键重复处理
     */
    @Test
    public void test06() {
        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8";
        String jdbcUsername = "admin";
        String jdbcPassword = "123456";

        // 获取 JDBC 链接
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            // 获取 jOOQ 执行器
            DSLContext dslContext = DSL.using(connection, SQLDialect.MYSQL);

            // 主键重复忽略插入

            int affecteRow = dslContext.insertInto(User.USER,
                            User.USER.ID, User.USER.NAME, User.USER.AGE, User.USER.CREATE_TIME)
                    .values(1, "username-1", 11, Timestamp.valueOf(LocalDateTime.now()))
                    .onDuplicateKeyIgnore()
                    .execute();
            // 这里执行完，返回affecteRow影响行数为0
            // 生成的SQL: insert ignore into `learn-jooq`.`User.USER` (`id`, `username`) values (1, 'username-1')


            // 主键重复进行更新

            dslContext.insertInto(User.USER)
                    .set(User.USER.ID, 1)
                    .set(User.USER.NAME, "小小白")
                    .set(User.USER.AGE, 8)
                    .onDuplicateKeyUpdate()
                    .set(User.USER.NAME, "小小小白")
                    .set(User.USER.AGE, 88)
                    .execute();
            // 生成SQL: insert into `learn-jooq`.`User.USER` (`id`, `username`, `address`) values
            // (1, 'duplicateKey-update', 'hello world') on duplicate key update `learn-jooq`.`User.USER`.`username` = 'duplicateKey-update',
            // `learn-jooq`.`User.USER`.`address` = 'update'
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 插入后获取自增主键
     */
    @Test
    public void test05() {
        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8";
        String jdbcUsername = "admin";
        String jdbcPassword = "123456";

        // 获取 JDBC 链接
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            // 获取 jOOQ 执行器
            DSLContext dslContext = DSL.using(connection, SQLDialect.MYSQL);

            UserRecord userRecord = dslContext.insertInto(User.USER,
                            User.USER.NAME, User.USER.AGE, User.USER.CREATE_TIME)
                    .values("小兰", 12, Timestamp.valueOf(LocalDateTime.now()))
                    .returning(User.USER.ID)
                    .fetchOne();
            
            System.out.println("userRecord = " + userRecord);
            System.out.println("userRecord.getId() = " + userRecord.getId());


            UserRecord record = dslContext.newRecord(User.USER);
            record.setName("小紫");
            record.setAge(30);
            record.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
            record.insert();
            // 这里的id是插入后数据库返回的自增ID，会自动存入record中，可以通过get方法获取
            System.out.println("record = " + record);
            System.out.println("record.getId() = " + record.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量插入
     */
    @Test
    public void test04() {
        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8";
        String jdbcUsername = "admin";
        String jdbcPassword = "123456";

        // 获取 JDBC 链接
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            // 获取 jOOQ 执行器
            DSLContext dslContext = DSL.using(connection, SQLDialect.MYSQL);

            List<UserRecord> userRecords = dslContext.select().from(User.USER).fetchInto(UserRecord.class);

            userRecords.forEach(r -> {
                r.setId(r.getId() + 10);
            });
            // 批量插入
            dslContext.batchInsert(userRecords).execute();

            // fetch方法可以返回一个结果集对象 Result
            // jOOQ的Result对象实现了List接口，可以直接当做集合使用
            Result<Record> recordResult = dslContext.select().from(User.USER).fetch();
            recordResult.forEach(record -> {
                Integer id = record.getValue(User.USER.ID);
                String username = record.getValue(User.USER.NAME);
                System.out.println("fetch Record     id: " + id + " , username: " + username);
            });


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入2
     */
    @Test
    public void test03() {
        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8";
        String jdbcUsername = "admin";
        String jdbcPassword = "123456";

        // 获取 JDBC 链接
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            // 获取 jOOQ 执行器
            DSLContext dslContext = DSL.using(connection, SQLDialect.MYSQL);

            UserRecord userRecord = dslContext.newRecord(User.USER);
            userRecord.setId(6);
            userRecord.setName("小资");
            userRecord.setAge(22);
            userRecord.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
            userRecord.insert();

            // fetch方法可以返回一个结果集对象 Result
            // jOOQ的Result对象实现了List接口，可以直接当做集合使用
            Result<Record> recordResult = dslContext.select().from(User.USER).fetch();
            recordResult.forEach(record -> {
                Integer id = record.getValue(User.USER.ID);
                String username = record.getValue(User.USER.NAME);
                System.out.println("fetch Record     id: " + id + " , username: " + username);
            });


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 插入1
     */
    @Test
    public void test02() {
        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8";
        String jdbcUsername = "admin";
        String jdbcPassword = "123456";

        // 获取 JDBC 链接
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            // 获取 jOOQ 执行器
            DSLContext dslContext = DSL.using(connection, SQLDialect.MYSQL);

            // 类SQL语法 insertInto 方法第一个参数通常是表常量
            dslContext.insertInto(User.USER, User.USER.ID, User.USER.NAME, User.USER.AGE, User.USER.CREATE_TIME)
                    .values(2, "小黑", 19, Timestamp.valueOf(LocalDateTime.now()))
                    .values(3, "小黄", 20, Timestamp.valueOf(LocalDateTime.now()))
                    .execute();

            // newRecord() 方法标识添加一条记录，通过链式调用，支持批量插入
            dslContext.insertInto(User.USER)
                    .set(User.USER.ID, 4)
                    .set(User.USER.NAME, "大黄")
                    .set(User.USER.AGE, 21)
                    .set(User.USER.CREATE_TIME, Timestamp.valueOf(LocalDateTime.now()))
                    .newRecord()
                    .set(User.USER.ID, 5)
                    .set(User.USER.NAME, "大红")
                    .set(User.USER.AGE, 22)
                    .set(User.USER.CREATE_TIME, Timestamp.valueOf(LocalDateTime.now()))
                    .execute();

            // fetch方法可以返回一个结果集对象 Result
            // jOOQ的Result对象实现了List接口，可以直接当做集合使用
            Result<Record> recordResult = dslContext.select().from(User.USER).fetch();
            recordResult.forEach(record -> {
                Integer id = record.getValue(User.USER.ID);
                String username = record.getValue(User.USER.NAME);
                System.out.println("fetch Record     id: " + id + " , username: " + username);
            });


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * JDBC
     */
    @Test
    public void test01() {
        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8";
        String jdbcUsername = "admin";
        String jdbcPassword = "123456";

        // 获取 JDBC 链接
        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            // 获取 jOOQ 执行器
            DSLContext dslContext = DSL.using(connection, SQLDialect.MYSQL);

            // fetch方法可以返回一个结果集对象 Result
            // jOOQ的Result对象实现了List接口，可以直接当做集合使用
            Result<Record> recordResult = dslContext.select().from(User.USER).fetch();
            recordResult.forEach(record -> {
                Integer id = record.getValue(User.USER.ID);
                String username = record.getValue(User.USER.NAME);
                System.out.println("fetch Record     id: " + id + " , username: " + username);
            });

            // 通过 Record.into 方法可以将默认Record对象，转换为表的Record对象，例如S1UserRecord
            // Result 接口也定义了into方法，可以将整个结果集转换为指定表Record的结果集
            // 通过 S1UserRecord 可以通过get方法直接获得表对象
            // 所有表的XXXRecord对象都是实现了 Record 对象的子类
            Result<UserRecord> userRecordResult = recordResult.into(User.USER);
            userRecordResult.forEach(record -> {
                Integer id = record.getId();
                String username = record.getName();
                System.out.println("into UserRecord   id: " + id + " , username: " + username);
            });

            // fetchInto方法可以可以传入任意class类型，或者表常量
            // 会直接返回任意class类型的List集合，或者指定表Record的结果集对象
            List<UserRecord> fetchIntoClassResultList = dslContext.select().from(User.USER).fetchInto(UserRecord.class);
            Result<UserRecord> fetchIntoTableResultList = dslContext.select().from(User.USER).fetchInto(User.USER);

            System.out.println("fetchIntoClassResultList: \n" + fetchIntoClassResultList.toString());
            System.out.println("fetchIntoTableResultList: \n" + fetchIntoTableResultList.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
