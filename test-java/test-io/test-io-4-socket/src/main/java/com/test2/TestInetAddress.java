package com.test2;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestInetAddress {

    public static void main(String[] args) throws UnknownHostException {
        InetAddress inetAddress1 = InetAddress.getByName("127.0.0.1");
        System.out.println(inetAddress1);

        InetAddress inetAddress2 = InetAddress.getByName("www.baidu.com");
        System.out.println(inetAddress2);

        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);
        System.out.println(localHost.getHostName());
        System.out.println(localHost.getHostAddress());
    }
}
