package com.test.tcpprotocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class NettyClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count = 0;

    // 当从服务器接收到一条消息时被调用
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        // 记录已接收的消息
        int len = msg.getLen();
        byte[] content = msg.getContent();
        System.out.println("Client received: " + "len = " + len + "\tcontent = "
                + new String(content, CharsetUtil.UTF_8));
        System.out.println("++count = " + ++count);
    }

    // 在到服务器的连接已建立之后将被调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            MessageProtocol messageProtocol = new MessageProtocol();
            byte[] content = ("Hello Server " + i + "\t").getBytes(CharsetUtil.UTF_8);
            messageProtocol.setContent(content);
            messageProtocol.setLen(content.length);
            ctx.writeAndFlush(messageProtocol);
        }
    }

    // 在处理过程中引发异常时调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 打印异常
        cause.printStackTrace();
        // 关闭Channel
        ctx.channel();
    }
}
