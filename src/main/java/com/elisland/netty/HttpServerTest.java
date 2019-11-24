package com.elisland.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Auther: jerry.feng
 * @Date: 2019/2/28
 * @Description: netty 作为服务器，使用案例
 */
public class HttpServerTest {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();//接收连接，将连接发送给worker
        EventLoopGroup workerGroup = new NioEventLoopGroup();//处理连接
        try {
            //服务端启动，启动服务端的工具
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ServerInitializerTest());//子处理器 ServerInitializerTest
            ChannelFuture sync = serverBootstrap.bind(8899).sync();
            sync.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
