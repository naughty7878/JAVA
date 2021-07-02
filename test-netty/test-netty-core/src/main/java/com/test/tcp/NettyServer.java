package com.test.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class NettyServer {

    public static void main(String[] args) throws InterruptedException {

        // 创建EventLoopGroup
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 创建ServerBootstrap
            ServerBootstrap bootstrap = new ServerBootstrap();

            // 配置bootstrap
            bootstrap.group(bossGroup, workerGroup)
                    // 指定使用的 NIO传输Channel
                    .channel(NioServerSocketChannel.class)
                    // 指定端口，设置套接字地址
                    .localAddress(new InetSocketAddress(9000))
                    // 添加处理器
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 初始化channel时，添加处理器
                            ch.pipeline().addLast(new NettyServerHandler());
                        }
                    });

            // 绑定端口
            // sync()：调用sync()方法阻塞等待知道绑定完成
            ChannelFuture channelFuture = bootstrap.bind().sync();
            // 获取Channel的CloseFuture，并阻塞知道它完成
            channelFuture.channel().closeFuture().sync();
        } finally {
            // 关闭释放资源
            bossGroup.shutdownGracefully().sync();
            workerGroup.shutdownGracefully().sync();
        }

    }
}
