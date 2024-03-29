package com.elisland.nioSocket;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @Auther: jerry.feng
 * @Date: 2019/6/25
 * @Description: 非阻塞式网络通信
 * <p>
 * /*
 * * 一、使用 NIO 完成网络通信的三个核心：
 * *
 * * 1. 通道（Channel）：负责连接
 * *
 * * 	   java.nio.channels.Channel 接口：
 * * 			|--SelectableChannel
 * * 				|--SocketChannel
 * * 				|--ServerSocketChannel
 * * 				|--DatagramChannel
 * *
 * * 				|--Pipe.SinkChannel
 * * 				|--Pipe.SourceChannel
 * *
 * * 2. 缓冲区（Buffer）：负责数据的存取
 * *
 * * 3. 选择器（Selector）：是 SelectableChannel 的多路复用器。用于监控 SelectableChannel 的 IO 状况
 * *
 * *
 */
public class TestNonBlockingNIO {
    public static void main(String[] args) throws ClassNotFoundException {
//        Class<?> aClass = Class.forName(args[0]);
//        Method[] declaredMethods = aClass.getDeclaredMethods();
//        for (String arg : args) {
//            System.out.println(arg);
//        }
//        for (int i=1;i< declaredMethods.length ;i++) {
//            System.out.println(declaredMethods[i]);
//        }
//

    }

    /**
     * 客户端
     */
//    @Test
//    public void client() throws IOException {
//        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8989));
//        socketChannel.configureBlocking(false);//开启非阻塞模式
//        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);//创建缓冲区
//        //发送数据给服务端
//        Scanner scan = new Scanner(System.in);
//
//        while (scan.hasNext()) {
//            String str = scan.next();
//            byteBuffer.put((new Date().toString() + "\n" + str).getBytes());
//            byteBuffer.flip();
//            socketChannel.write(byteBuffer);
//            byteBuffer.clear();
//        }
//
//        //关闭通道
//        socketChannel.close();
//    }
    @Test
    public void server() throws IOException {
        ServerSocketChannel ssChannel = ServerSocketChannel.open();//获取通道
        //切换非阻塞模式
        ssChannel.configureBlocking(false);
        //绑定监听端口
        ssChannel.bind(new InetSocketAddress("127.0.0.1",8989));
        //获取选择器
//        DatagramChannel datagramChannel = SelectorProvider.provider().openDatagramChannel();
        Selector selector = Selector.open();
        //将通道注册到选择器上, 并且指定“监听接收事件”
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        //6. 轮询式的获取选择器上已经“准备就绪”的事件
        while(selector.select() > 0){

            //7. 获取当前选择器中所有注册的“选择键(已就绪的监听事件)”
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();

            while(it.hasNext()){
                //8. 获取准备“就绪”的是事件
                SelectionKey sk = it.next();
                //9. 判断具体是什么事件准备就绪
                if(sk.isAcceptable()){
                    //10. 若“接收就绪”，获取客户端连接
                    SocketChannel sChannel = ssChannel.accept();

                    //11. 切换非阻塞模式
                    sChannel.configureBlocking(false);

                    //12. 将该通道注册到选择器上
                    sChannel.register(selector, SelectionKey.OP_READ);
                }else if(sk.isReadable()){
                    //13. 获取当前选择器上“读就绪”状态的通道
                    SocketChannel sChannel = (SocketChannel) sk.channel();

                    //14. 读取数据
                    ByteBuffer buf = ByteBuffer.allocate(1024);

                    int len = 0;
                    while((len = sChannel.read(buf)) > 0 ){
                        buf.flip();
                        System.out.println(new String(buf.array(), 0, len));
                        buf.clear();
                    }
                }

                //15. 取消选择键 SelectionKey
                it.remove();
            }
        }
    }
}
