package com.elisland.customprotocol;

/**
 * @Auther: jerry.feng
 * @Date: 2019/6/23
 * @Description: 自动以消息协议
 */
public class MessageProtocol {
    /**
     * 消息长度
     */
    private int length;

    /**
     * 消息指令
     */
    private byte order;

    /**
     * 消息内容
     */
    private String content;


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
