package com.test.rpc.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    private ChannelHandlerContext context;//上下文
    //返回的结果
    private String result;
    // 客户端请求服务端的请求内容
    private String req;


    // 与服务器的连接创建后，就会被调用, 这个方法是第一个被调用(1)
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("NettyClientHandler#channelActive()");
        // 在call()方法中，需要使用上下文发送消息
        context = ctx;
    }

    //收到服务器的数据后，调用方法 (4)
    //
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("NettyClientHandler#channelRead()");
        System.out.println("msg = " + msg);
        // 设置返回值
        result = msg.toString();
        //唤醒等待的线程
        notify();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    //被代理对象调用, 发送数据给服务器，-> wait -> 等待被唤醒(channelRead) -> 返回结果 (3)-》5
    @Override
    public synchronized Object call() throws Exception {
        System.out.println(" call1 被调用  ");
        System.out.println("req = " + req);
        context.writeAndFlush(req);
        // 进行wait
        // 等待channelRead 方法获取到服务器的结果后，唤醒
        wait();
        System.out.println(" call2 被调用  ");
        // 服务方返回的结果，返回到调用的业务方
        return  result;

    }

    void setReq(String req) {
        this.req = req;
    }
}
