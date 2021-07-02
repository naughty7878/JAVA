package com.test.tcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        // 事件循环组
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            // 启动对象
            Bootstrap bootstrap = new Bootstrap();
            // 配置
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress("127.0.0.1",9000))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyClientHandler());
                        }
                    });

            // 连接远程地址，阻塞至连接完成
            ChannelFuture channelFuture = bootstrap.connect().sync();
            // 阻塞直到channel关闭
            channelFuture.channel().closeFuture().sync();
        } finally {
            // 关闭释放资源
            group.shutdownGracefully().sync();
        }
    }
}
