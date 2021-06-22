package com.test.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * 1、SimpleChannelInboundHandler<I> extends ChannelInboundHandlerAdapter
 * 2、HttpObject 客户端和服务端相互通讯的数据被封装成 HttpObject
 */
public class MyHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    // channelRead0 读取客户端数据
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        // 判断 msg 是不是 httprequest 请求
        if (msg instanceof HttpRequest) {
            System.out.println("msg.getClass() = " + msg.getClass());
            System.out.println("ctx.channel().remoteAddress() = " + ctx.channel().remoteAddress());

            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
            System.out.println("ctx.pipeline().hashCode() = " + ctx.pipeline().hashCode());
            System.out.println("this.hashCode() = " + this.hashCode());
            System.out.println("ctx.channel().hashCode() = " + ctx.channel().hashCode());
            System.out.println("ctx.channel().eventLoop().hashCode() = " + ctx.channel().eventLoop().hashCode());

            // 获取HttpRequest
            HttpRequest httpRequest = (HttpRequest) msg;
            // 通过uri过滤资源
            URI uri = new URI(httpRequest.uri());
            if ("/favicon.ico".equals(uri.getPath())) {
                return;
            }

            // 回复信息给浏览器[http协议]
            ByteBuf content = Unpooled.copiedBuffer("hello 我是服务器～", CharsetUtil.UTF_8);

            // 构造一个http的响应，即 httpresponse
            DefaultFullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);

            httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=UTF-8");
            httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            // 将够好的 response 返回
            ctx.writeAndFlush(httpResponse);
        }
    }
}
