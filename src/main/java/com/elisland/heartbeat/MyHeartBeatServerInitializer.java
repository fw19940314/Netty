package com.elisland.heartbeat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: jerry.feng
 * @Date: 2019/3/1
 * @Description:
 */
public class MyHeartBeatServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new IdleStateHandler(5,7,10,TimeUnit.SECONDS));//空闲检测
        pipeline.addLast(new MyHeartBeatServerHandle());//自定义handle

    }
}
