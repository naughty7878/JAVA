package com.test.zookeeper.leaderselector;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * -DappName=app1
 */
public class LeaderSelectorTest {

    private static String zookeeperConnectionString = "120.78.189.168:12181,120.78.189.168:12182,120.78.189.168:12183";

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {

        String appName = System.getProperty("appName");
        // 创建客户端
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(5 * 1000, 10);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(zookeeperConnectionString, retryPolicy);
        // 启动客户端
        curatorFramework.start();

        // 创建监听器
        LeaderSelectorListener listener = new LeaderSelectorListenerAdapter() {
            // 选举 leader后的回调
            @Override
            public void takeLeadership(CuratorFramework client) throws Exception {
                // this callback will get called when you are the leader
                // do whatever leader work you need to and only exit
                // this method when you want to relinquish leadership
                System.out.println(" LEADER .  【" + appName + "】, Pre Warm Cache  ");
                // 模拟业务耗时操作
                TimeUnit.SECONDS.sleep(5);
            }
        };

        // 领导选择器
        LeaderSelector selector = new LeaderSelector(curatorFramework, "/cache_warmer_leader", listener);
        selector.autoRequeue();  // not required, but this is behavior that you will probably expect
        // 启动
        selector.start();

        // 等待
        countDownLatch.await();
    }
}
