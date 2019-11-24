package com.elisland.chat;

import com.elisland.socket.MyClientInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Auther: jerry.feng
 * @Date: 2019/3/1
 * @Description: 服务端
 */
public class MyChatServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();//接收连接，将连接发送给worker
        EventLoopGroup workerGroup = new NioEventLoopGroup();//处理连接
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
//                    .childHandler(new ChannelInitializer<SocketChannel>() {
//                        @Override
//                        protected void initChannel(SocketChannel ch) throws Exception {
//                            ch.pipeline().addLast(new MyClientInitializer());
//                        }
//                    });
                    .childHandler(new MyChatServerInitializer());//处理器
            ChannelFuture sync = serverBootstrap.bind(8899).sync();
            sync.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();//关闭
            workerGroup.shutdownGracefully();
        }
    }
}
