package com.test.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

    // 定义一个channel组，管理所有的channel
    // GlobalEventExecutor 全局事件执行器，是单例的
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    // handlerAdded 表示连接一旦建立，第一个被执行
    // 将当前Channel加入到channelGroup
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        LocalDateTime dateTime = LocalDateTime.now();
        // 将该客户端加入聊天的信息推送给其他客户端
        // writeAndFlush():会给channelGroup 中所有的 channel 发送消息
        channelGroup.writeAndFlush(dateTime.format(dateTimeFormatter) + " [客户端]" + channel.remoteAddress() + " 加入聊天～\n");
        // 将当前Channel加入到channelGroup
        channelGroup.add(channel);
        System.out.println(dateTime.format(dateTimeFormatter) + " 群聊人数：" + channelGroup.size());
    }

    // 表示channel处于活动状态
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime.format(dateTimeFormatter) + " " + ctx.channel().remoteAddress() + " 上线了～");
    }

    // 表示channel处于非活动状态
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime.format(dateTimeFormatter) + " " + ctx.channel().remoteAddress() + " 下线了～");
    }

    // 表示channel断开连接
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        LocalDateTime dateTime = LocalDateTime.now();
        // channelGroup 会自动移除 channel
        // channelGroup.remove(channel);
        channelGroup.writeAndFlush(dateTime.format(dateTimeFormatter) + " " + "[客户端]" + channel.remoteAddress() + " 离开聊天～\n");
        System.out.println(dateTime.format(dateTimeFormatter) + " 群聊人数：" + channelGroup.size());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        LocalDateTime dateTime = LocalDateTime.now();
        // 遍历 channelGroup，根据不同的情况，回送不同的消息
        channelGroup.forEach(ch -> {
            if (channel != ch) {
                ch.writeAndFlush(dateTime.format(dateTimeFormatter) + " [客户端]" + channel.remoteAddress() + " 发送消息：" + msg + "\n");
            } else {
                ch.writeAndFlush(dateTime.format(dateTimeFormatter) + " [服务器]：发送成功\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
