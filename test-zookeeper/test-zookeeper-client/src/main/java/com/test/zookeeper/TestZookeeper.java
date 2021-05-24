package com.test.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class TestZookeeper {

    private String connectString = "120.78.189.168:12181,120.78.189.168:12182,120.78.189.168:12183";

    private int sessionTimeout = 2000;

    private ZooKeeper zkClient;

    // 初始化客户端
    @Before
    public void init() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            public void process(WatchedEvent event) {
                System.out.println(event.toString());
            }
        });
    }

    // 创建节点
    @Test
    public void createNode() throws InterruptedException, KeeperException {
        String path = zkClient.create("/javaClient", "abc".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(path);
    }

    // 获取子节点 并监控数据的变化
    @Test
    public void getDataAndWatch() throws InterruptedException, KeeperException {
        List<String> children = zkClient.getChildren("/", true);
        for (String child : children) {
            System.out.println(child);
        }

        Thread.sleep(Integer.MAX_VALUE);
    }

    // 判断节点是否存在
    @Test
    public void exist() throws InterruptedException, KeeperException {
        Stat exists = zkClient.exists("/javaClient", false);
        System.out.println(exists == null ? "not exists": "exists");
    }
}
