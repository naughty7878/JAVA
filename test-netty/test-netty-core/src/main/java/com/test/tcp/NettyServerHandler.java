package com.test.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.UUID;

public class NettyServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private int count = 0;

    // 对于每个传入的消息都要调用
    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        // 记录已接收的消息
        System.out.println("Server received: " + msg.toString(CharsetUtil.UTF_8));
        System.out.println("++count = " + ++count);

        // 发送消息
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        ByteBuf byteBuf = Unpooled.copiedBuffer(uuid + "\t", CharsetUtil.UTF_8);
        ctx.writeAndFlush(byteBuf);
    }


    // 在读取操作期间，有异常抛出时调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 打印异常
        cause.printStackTrace();
        // 关闭Channel
        ctx.channel();
    }
}
