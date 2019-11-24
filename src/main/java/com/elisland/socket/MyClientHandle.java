package com.elisland.socket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * @Auther: jerry.feng
 * @Date: 2019/2/28
 * @Description:
 */
public class MyClientHandle extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("服务器端地址：" + ctx.channel().remoteAddress());
        System.out.println("服务器向客户端发送的数据：" + msg);
        ctx.writeAndFlush("this meg from client:" + LocalDateTime.now());
    }

    /**
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("来自客户端--");
//        super.channelActive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
