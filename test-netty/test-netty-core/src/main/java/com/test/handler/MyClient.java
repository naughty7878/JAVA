package com.test.handler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class MyClient {
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
                            ChannelPipeline pipeline = ch.pipeline();
                            // 添加编码器
                            pipeline.addLast(new MyLongToByteEncoder());
                            // 解码器
                            pipeline.addLast(new MyByteToLongDecoder());
                            // 添加业务处理器
                            pipeline.addLast(new MyClientHandler());
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
