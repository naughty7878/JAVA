package com.test.rpc.netty;


import com.test.rpc.customer.ClientBootstrap;
import com.test.rpc.provider.HelloServiceImpl;
import com.test.rpc.service.HelloService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

// 服务器这边handler比较简单
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 获取客户端发送的消息，并调用服务
        System.out.println("msg = " + msg);
        // 客户端在调用服务器的api 时，我们需要定义一个协议
        // 协议前缀
        if(msg.toString().startsWith(HelloService.MSG_PREFIX)) {
            String substring = msg.toString().substring(msg.toString().lastIndexOf("#") + 1);
            String result = new HelloServiceImpl().hello(substring);
            ctx.writeAndFlush(result);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
