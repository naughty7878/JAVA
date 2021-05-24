package com.test.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DistributeClient {

    private String connectString = "120.78.189.168:12181,120.78.189.168:12182,120.78.189.168:12183";

    private int sessionTimeout = 2000;

    private ZooKeeper zkClient;

    private void getConnnet() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            public void process(WatchedEvent event) {
                System.out.println("客户端监听：" + event.toString());

                try {
                    // 再次监听
                    getChildren();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (KeeperException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getChildren() throws InterruptedException, KeeperException {
        List<String> path = zkClient.getChildren("/servers", true);
        // 存储服务器节点名称集合
        List<String> hosts = new ArrayList<String>();
        for (String s : path) {
            byte[] data = zkClient.getData("/servers/" + s, false, null);
            hosts.add(new String(data));
        }
        // 打印所有服务器名称
        System.out.println("所有服务器名称" + hosts);
    }

    private void business() throws InterruptedException {
        Thread.sleep(Integer.MAX_VALUE);
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        DistributeClient client = new DistributeClient();
        // 1、连接zookeeper
        client.getConnnet();

        // 2、注册节点
        client.getChildren();

        // 3、业务逻辑
        client.business();
    }
}
