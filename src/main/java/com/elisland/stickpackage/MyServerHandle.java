package com.elisland.stickpackage;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * @Auther: jerry.feng
 * @Date: 2019/6/23
 * @Description:
 */
public class MyServerHandle extends SimpleChannelInboundHandler<ByteBuf> {
    private int count;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);
        String message = new String(
                bytes, Charset.forName("utf-8")
        );
        System.out.println("服务端接受到消息"+message);
        System.out.println("服务端接受到消息数量"+(++count));

        //向客户端返回数据
        ByteBuf buffer = Unpooled.copiedBuffer(UUID.randomUUID().toString(),Charset.forName("utf-8"));
        ctx.writeAndFlush(buffer);
    }
}
