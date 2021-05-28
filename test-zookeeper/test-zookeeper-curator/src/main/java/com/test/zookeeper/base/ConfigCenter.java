package com.test.zookeeper.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ConfigCenter {

    private static String connectString = "120.78.189.168:12181,120.78.189.168:12182,120.78.189.168:12183";

    private static int sessionTimeout = 30 * 2000;

    private static ZooKeeper zkClient;

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println(event.toString());
                if (event.getType() == Event.EventType.None && event.getState() == Event.KeeperState.SyncConnected) {
                    System.out.println("连接已建立");
                    countDownLatch.countDown();
                }
            }
        });
        // 等待完成连接
        countDownLatch.await();

        MyConfig myConfig = new MyConfig();
        myConfig.setKey("anyKey");
        myConfig.setName("anyName");
        byte[] bytes = objectMapper.writeValueAsBytes(myConfig);

        // 创建节点
        Stat stat = zkClient.exists("/myconfig", null);
        System.out.println(stat);
        if (stat == null) {
            zkClient.create("/myconfig", bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }

        Watcher watcher = new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                try {
                    if(event.getType() == Event.EventType.NodeDataChanged
                            && event.getPath() != null && event.getPath().equals("/myconfig")) {
                        System.out.println( event.getPath() + " 发生数据变化");

                        byte[] data = zkClient.getData("/myconfig", this, null);
                        MyConfig newMyConfig = objectMapper.readValue(new String(data), MyConfig.class);
                        System.out.println("变化数据：" + newMyConfig);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // 获取数据，并注册监听
        byte[] data = zkClient.getData("/myconfig", watcher, null);
        System.out.println("原始数据：" + objectMapper.readValue(new String(data), MyConfig.class));

        TimeUnit.SECONDS.sleep(10000);
    }


}
