package com.elisland.stickpackage;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;

/**
 * @Auther: jerry.feng
 * @Date: 2019/6/23
 * @Description:
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new FixedLengthFrameDecoder(15));
        pipeline.addLast(new MyServerHandle());//自定义handle
    }
}
