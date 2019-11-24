package com.elisland.protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther: jerry.feng
 * @Date: 2019/6/15
 * @Description: 自定义处理器
 */
public class CustomServerHandler extends SimpleChannelInboundHandler<MessageData.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageData.MyMessage msg) throws Exception {
        System.out.println(msg);
    }
}
