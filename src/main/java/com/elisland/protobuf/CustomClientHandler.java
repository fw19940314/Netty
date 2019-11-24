package com.elisland.protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * @Auther: jerry.feng
 * @Date: 2019/6/15
 * @Description:
 */
public class CustomClientHandler extends SimpleChannelInboundHandler<MessageData.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageData.MyMessage msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int num = new Random().nextInt(3);
        MessageData.MyMessage myMessage = null;
        if (0 == num){
            myMessage = MessageData.MyMessage.newBuilder()
                    .setDataType(MessageData.MyMessage.DataType.DogType)
                    .setDog(MessageData.Dog.newBuilder()
                            .setId(1002)
                            .setName("旺财")
                            .build()).build();
        }else if (1==num){
            myMessage = MessageData.MyMessage.newBuilder()
                    .setDataType(MessageData.MyMessage.DataType.CatType)
                    .setCat(MessageData.Cat.newBuilder()
                            .setId(2001)
                            .setName("tom")
                            .build()).build();
        }else if (2 == num){
            myMessage = MessageData.MyMessage.newBuilder()
                    .setDataType(MessageData.MyMessage.DataType.PersonType)
                    .setPerson(MessageData.Person.newBuilder()
                            .setId(2001)
                            .setEmail("8801230@gmail.com")
                            .setName("jack")
                            .build()).build();
        }
        ctx.channel().writeAndFlush(myMessage);//发送至服务器端

//        super.channelActive(ctx);
//        Student.Person person = Student.Person.newBuilder()
//                .setId(1002)
//                .setEmail("2109@gmail.com")
//                .setName("tom")
//                .build();
//        ctx.channel().writeAndFlush(person);//发送至服务器端
    }

}
