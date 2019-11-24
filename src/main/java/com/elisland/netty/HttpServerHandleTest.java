package com.elisland.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URL;

/**
 * @Auther: jerry.feng
 * @Date: 2019/2/28
 * @Description: 处理器
 */
public class HttpServerHandleTest extends SimpleChannelInboundHandler<HttpObject> {
    /**
     * 读取客户端发送来的请求，并向客户端响应
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if (msg instanceof HttpRequest) {
            HttpRequest httpRequest = (HttpRequest) msg;
            System.out.println("客户端请求：" + httpRequest.method().name());
            URL url = new URL(httpRequest.uri());
            if ("/favicon.ico".equals(url.getPath())) {
                System.out.println("浏览器请求favicon");
                return;
            }

            ByteBuf content = Unpooled.copiedBuffer("Hello world!", CharsetUtil.UTF_8);
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            ctx.writeAndFlush(response);
        }
    }
}
