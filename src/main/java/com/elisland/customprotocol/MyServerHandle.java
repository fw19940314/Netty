package com.elisland.customprotocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @Auther: jerry.feng
 * @Date: 2019/6/23
 * @Description:
 */
public class MyServerHandle extends SimpleChannelInboundHandler<CustomMessageData.MessageData> {
    int count;//记录接收数据数量

    /**
     * 接受client发送来的消息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CustomMessageData.MessageData msg) throws Exception {
        System.out.println("MyServerHandle-----");
        long length = msg.getLength();
        CustomMessageData.MessageData.DataType order = msg.getOrder();
        String ContentData = msg.getContent().getData();
        System.out.println("服务端接收到的数据长度:" + length);
        System.out.println("服务端接收到的数据指令:" + order);
        System.out.println("服务端接收到的数据内容:" + ContentData);
        System.out.println("服务端接收到的数据数量:" + (++count)+"\t\n");

        String sendClientMessage = UUID.randomUUID().toString();
        int sendClientMessageLength = sendClientMessage.getBytes("utf-8").length;
        //每次收到客户端消息后，向客户端返回UUID字符串
        CustomMessageData.MessageData message = CustomMessageData.MessageData.newBuilder()
                .setLength(sendClientMessageLength)
                .setOrder(CustomMessageData.MessageData.DataType.forNumber(1))
                .setContent(CustomMessageData.MessageData.Content.newBuilder().setData(sendClientMessage)).build();
        ctx.writeAndFlush(message);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
