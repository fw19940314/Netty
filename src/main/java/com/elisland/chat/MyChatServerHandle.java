package com.elisland.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Auther: jerry.feng
 * @Date: 2019/3/1
 * @Description: 当客户端
 */
public class MyChatServerHandle extends SimpleChannelInboundHandler<String> {
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * @param ctx ChannelHandlerContext channle 上下文
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.forEach(x -> {
            if (channel != x) {
                x.writeAndFlush(channel.remoteAddress() + "发送的消息：" + msg + "\n");
            } else {
                x.writeAndFlush("【自己】" + msg +"\n");
            }
        });
    }

    /**
     * 出现异常处理
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 客户端连接完成后，回调函数
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[服务器] - " + channel.remoteAddress() + "上线！\n");
        channelGroup.add(channel);
    }

    /**
     * 连接断掉
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[服务器] - " + channel.remoteAddress() + "离开！\n");
        channelGroup.remove(channel);
    }

    /**
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "上线！\n");
    }

    /**
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "下线！\n");
    }
}
