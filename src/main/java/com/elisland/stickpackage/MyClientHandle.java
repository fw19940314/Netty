package com.elisland.stickpackage;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

/**
 * @Auther: jerry.feng
 * @Date: 2019/6/23
 * @Description:
 */
public class MyClientHandle extends SimpleChannelInboundHandler<ByteBuf> {
    int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);
        String message = new String(bytes, Charset.forName("utf-8"));
        System.out.println("客户端接收到消息："+message);
        System.out.println("客户端接收到消息次数："+(++count));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            ByteBuf byteBuf = Unpooled.copiedBuffer("send for client", Charset.forName("utf-8"));
            ctx.writeAndFlush(byteBuf);
        }

    }

    public static void main(String[] args) {
        String ss = "send for client";
        System.out.println(ss.length());
    }
}
