package com.elisland.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Auther: jerry.feng
 * @Date: 2019/3/1
 * @Description: 客户端
 */
public class MyChatClient {
    public static void main(String[] args) throws InterruptedException, IOException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();//①创建EventLoopGroup
        try {
            Bootstrap bootstrap = new Bootstrap();//②创建Server-Bootstrap
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)//③指定所有使用NIO传输channel
                    .handler(new MyChatClientInitializer());//④添加Handler

            Channel channel = bootstrap.connect("localhost", 8899).sync().channel();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            for (; ; ) {
                channel.writeAndFlush(bufferedReader.readLine() + "\t\n");
            }
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
