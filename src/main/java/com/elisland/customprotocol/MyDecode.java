package com.elisland.customprotocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @Auther: jerry.feng
 * @Date: 2019/6/23
 * @Description: 自定义解码器
 */
public class MyDecode extends ReplayingDecoder {
    /**
     * 解码器
     * @param ctx
     * @param in
     * @param out
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("Mydecode invoke...");
        int length = in.readInt();
        byte order = in.readByte();
        byte[] content = new byte[length];
        in.readBytes(content);

        CustomMessageData.MessageData decodeData = CustomMessageData.MessageData.newBuilder()
                .setLength(length)
                .setOrder(CustomMessageData.MessageData.DataType.forNumber(order))
                .setContent(CustomMessageData.MessageData.Content.newBuilder()
                        .setData(new String(content, Charset.forName("utf-8")))).build();
         out.add(decodeData);
    }
}
