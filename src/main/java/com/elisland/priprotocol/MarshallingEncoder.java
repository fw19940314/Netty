package com.elisland.priprotocol;

import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.AbstractMarshaller;
import org.jboss.marshalling.ByteOutput;
import org.jboss.marshalling.Marshaller;
import org.jboss.marshalling.Marshalling;

import java.io.IOException;

/**
 * @Auther: jerry.feng
 * @Date: 2019/6/21
 * @Description: 消息编码工具类
 */
public class MarshallingEncoder {
    private static final byte[] LENGTH_PLACEHOLDER = new byte[4];
    Marshaller marshaller;

    public MarshallingEncoder() throws IOException {
        marshaller = MarshallingCodecFactory.buildMarshalling();
    }

    // 使用marshall对Object进行编码，并且写入bytebuf...
    protected void encode(Object msg, ByteBuf out) throws Exception {
        try {
            //1. 获取写入位置
            int lengthPos = out.writerIndex();
            //2. 先写入4个bytes，用于记录Object对象编码后长度
            out.writeBytes(LENGTH_PLACEHOLDER);
            //3. 使用代理对象，防止marshaller写完之后关闭byte buf
            ChannelBufferByteOutput output = new ChannelBufferByteOutput(out);
            //4. 开始使用marshaller往bytebuf中编码
            marshaller.start(output);
            marshaller.writeObject(msg);
            //5. 结束编码
            marshaller.finish();
            //6. 设置对象长度
            out.setInt(lengthPos, out.writerIndex() - lengthPos - 4);
        } finally {
            marshaller.close();
        }
    }
}
