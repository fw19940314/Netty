package com.elisland.socket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @Auther: jerry.feng
 * @Date: 2019/2/28
 * @Description:
 */
public class MyServerHandle extends SimpleChannelInboundHandler<String> {
    /**
     * @param ctx ChannelHandlerContext channle 上下文
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("客户端地址：" + ctx.channel().remoteAddress() + ", " + msg);
        ctx.channel().writeAndFlush("from server :"+UUID.randomUUID().toString());
    }

    /**
     * 出现异常处理
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
