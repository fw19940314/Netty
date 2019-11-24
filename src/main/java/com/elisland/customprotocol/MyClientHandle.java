package com.elisland.customprotocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther: jerry.feng
 * @Date: 2019/6/23
 * @Description:
 */
public class MyClientHandle extends SimpleChannelInboundHandler<CustomMessageData.MessageData> {
    int count;

    /**
     * 客户端和服务端建立连接后，向服务端发送消息
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyClientHandle----");
        for (int i = 0; i < 10; i++) {
            Thread.sleep(500);
            String message = "message for client";
            int length = message.getBytes().length;
            MessageProtocol messageProtocol = new MessageProtocol();
            messageProtocol.setLength(length);
            messageProtocol.setContent(message);
            //构建消息内容
            CustomMessageData.MessageData messageData = CustomMessageData.MessageData.newBuilder()
                    .setLength(length)
                    .setOrder(CustomMessageData.MessageData.DataType.forNumber(1))
                    .setContent(CustomMessageData.MessageData.Content.newBuilder().setData(message)).build();
            ctx.writeAndFlush(messageData);
        }
    }

    /**
     * 接受响应服务端消息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CustomMessageData.MessageData msg) throws Exception {
        long length = msg.getLength();
        CustomMessageData.MessageData.DataType order = msg.getOrder();
        String data = msg.getContent().getData();
        System.out.println("客户端接收到的数据长度:" + length);
        System.out.println("客户端接收到的数据指令:" + order);
        System.out.println("客户端接收到的数据内容:" + data);
        System.out.println("客户端接收到的数据数量:" + (++count)+"\t\n");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
