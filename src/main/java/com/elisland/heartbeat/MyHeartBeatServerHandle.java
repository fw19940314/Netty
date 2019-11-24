package com.elisland.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @Auther: jerry.feng
 * @Date: 2019/3/1
 * @Description: 当客户端
 */
public class MyHeartBeatServerHandle extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateHandler) {
            IdleStateEvent evt1 = (IdleStateEvent) evt;
            String eventType = null;
            switch (evt1.state()) {
                case READER_IDLE:
                    eventType = "读空闲";
                case WRITER_IDLE:
                    eventType = "写空闲";
                case ALL_IDLE:
                    eventType = "读写空闲";
            }
            System.out.println(ctx.channel().remoteAddress() + "超时事件:" + eventType);
            ctx.channel().close();
        }
    }
}
