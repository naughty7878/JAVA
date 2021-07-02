package com.test.codec;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;

/**
 * 1) 实例要求:使用IDEA创建Netty项目
 * 2) Netty 服务器在 6668 端口监听，客户端能发送消息给服务器 "hello, 服务器~"
 * 3) 服务器可以回复消息给客户端"hello,客户端~"
 * 4) 目的:对Netty线程模型有一个初步认识,便于理解Netty模型理论
 */
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        // 创建BossGroup 和 WorkerGroup 2个线程组，两个都是无限循环
        // bossGroup 只处理连接请求，WorkerGroup 处理客户端业务
        // bossGroup 和 workerGroup 含有的子线程(NioEventLoop)的个数
        // 默认实际 cpu核数 * 2
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 创建服务器端的启动对象，配置参数
            ServerBootstrap  bootstrap = new ServerBootstrap();
            // 使用链式编程来设置启动对象
            bootstrap.group(bossGroup, workerGroup) // 设置两个线程组
                    .channel(NioServerSocketChannel.class)  // 使用 NioSocketChannel 作为服务器通道
                    .option(ChannelOption.SO_BACKLOG, 128) // 设置线程队列的连接数
                    .childOption(ChannelOption.SO_KEEPALIVE, true) // 设置保存活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() { // 创建一个通道测试对象
                        // 给pipeline 设置处理器
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //在pipeline加入ProtoBufDecoder
                            //指定对哪种对象进行解码
                            pipeline.addLast("decoder", new ProtobufDecoder(StudentPOJO.Student.getDefaultInstance()));
                            pipeline.addLast(new NettyServerHandler());
                        }
                    }); // 给 workerGroup 的 EventLoop 对应的管道设置处理器

            System.out.println("～～～Netty Server Is Ready～～～");

            // 绑定一个端口并且同步，生成一个 channelFuture 对象
            ChannelFuture channelFuture = bootstrap.bind(6666).sync();

            // 给 channelFuture 注册监听器
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("端口绑定成功");
                    }else {
                        System.out.println("端口绑定失败");
                    }
                }
            });

            // 对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭线程组
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
