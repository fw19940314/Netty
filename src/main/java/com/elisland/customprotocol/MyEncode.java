package com.elisland.customprotocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Auther: jerry.feng
 * @Date: 2019/6/23
 * @Description: 自定义编码器
 */
public class MyEncode extends MessageToByteEncoder<CustomMessageData.MessageData> {

    /**
     * 编码器
     * @param ctx
     * @param msg
     * @param out
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, CustomMessageData.MessageData msg, ByteBuf out) throws Exception {
        System.out.println("MyEncode invoke...");
        out.writeInt(((int) (msg.getLength())));
        out.writeByte((byte)msg.getOrder().getNumber());
        out.writeBytes(msg.getContent().getData().getBytes());
    }
}
