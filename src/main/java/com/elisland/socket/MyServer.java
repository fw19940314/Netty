package com.elisland.socket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Auther: jerry.feng
 * @Date: 2019/2/28
 * @Description: netty 服务器端
 */
public class MyServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();//接收连接，将连接发送给worker
        EventLoopGroup workerGroup = new NioEventLoopGroup();//处理连接
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MyServerInitializer());//处理器
            ChannelFuture sync = serverBootstrap.bind(8899).sync();
            sync.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();//关闭
            workerGroup.shutdownGracefully();
        }
    }
    private static byte[] encode(){
//     SubscribeReqProto
        return null;
    }
}
