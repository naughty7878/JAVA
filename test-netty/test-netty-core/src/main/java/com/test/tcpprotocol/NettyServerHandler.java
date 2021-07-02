package com.test.tcpprotocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.UUID;

public class NettyServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count = 0;

    // 对于每个传入的消息都要调用
    @Override
    public void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        // 记录已接收的消息
        System.out.println("Server received: " + new String(msg.getContent(), CharsetUtil.UTF_8));
        System.out.println("++count = " + ++count);

        // 发送消息
        MessageProtocol messageProtocol = new MessageProtocol();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        byte[] content = uuid.getBytes(CharsetUtil.UTF_8);
        messageProtocol.setContent(content);
        messageProtocol.setLen(content.length);
        ctx.writeAndFlush(messageProtocol);
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
