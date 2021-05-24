package com.test.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;

public class DistributeServer {

    private String connectString = "120.78.189.168:12181,120.78.189.168:12182,120.78.189.168:12183";

    private int sessionTimeout = 2000;

    private ZooKeeper zkClient;

    private void getConnnet() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            public void process(WatchedEvent event) {
                System.out.println("服务端监听：" + event.toString());
            }
        });
    }

    private void register(String hostname) throws InterruptedException, KeeperException {
        String path = zkClient.create("/servers/server", hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(hostname + " is online");
    }

    private void business() throws InterruptedException {
        Thread.sleep(Integer.MAX_VALUE);
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        DistributeServer server = new DistributeServer();
        // 1、连接zookeeper
        server.getConnnet();

        // 2、注册节点
        server.register(args[0]);

        // 3、业务逻辑
        server.business();
    }
}
