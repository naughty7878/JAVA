package com.test.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.transaction.CuratorOp;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestCurator {

    //会话超时时间
    private static final int SESSION_TIMEOUT = 30 * 1000;

    //连接超时时间
    private static final int CONNECTION_TIMEOUT = 3 * 1000;

    //ZooKeeper服务地址
    private static final String CONNECT_ADDR = "120.78.189.168:12181,120.78.189.168:12182,120.78.189.168:12183";

    //创建连接实例
    private CuratorFramework client = null;

    @Before
    public void init() throws Exception {
        //1 重试策略：初试时间为1s 重试10次
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
        //2 通过工厂创建连接
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(CONNECT_ADDR)
                .connectionTimeoutMs(CONNECTION_TIMEOUT)
                .sessionTimeoutMs(SESSION_TIMEOUT)
                .retryPolicy(retryPolicy)
//命名空间           .namespace("super")
                .build();
        //3 开启连接
        client.start();
    }

    @Test
    public void test1() throws Exception {
        //定义几个基本操作
        CuratorOp createOp = client.transactionOp().create()
                .forPath("/curator/one_path", "some data".getBytes());

        CuratorOp setDataOp = client.transactionOp().setData()
                .forPath("/curator", "other data".getBytes());

        CuratorOp deleteOp = client.transactionOp().delete()
                .forPath("/curator");

        //事务执行结果
        List<CuratorTransactionResult> results = client.transaction()
                .forOperations(createOp, setDataOp, deleteOp);

        //遍历输出结果
        for (CuratorTransactionResult result : results) {
            System.out.println("执行结果是： " + result.getForPath() + "--" + result.getType());
        }
    }
}
