package com.test.jvm;

/**
 * 测试方法区默认大小
 *
 *  JDK7及以前
 *  设置参数：-XX:PermSize=100m -XX:MaxPermSize=100m
 *
 *  JDK8及以后
 *  设置参数：-XX:MetaspaceSize=100m -XX:MaxMetaspaceSize=100m
 *
 *  查看
 *      命令：jps
 *      命令：jinfo -flag MetaspaceSize pid
 *
 */
public class MethodAreaTest {
    public static void main(String[] args) {
        System.out.println("start...");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end...");
    }
}
