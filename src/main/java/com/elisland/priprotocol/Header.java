package com.elisland.priprotocol;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: jerry.feng
 * @Date: 2019/6/21
 * @Description:
 */
public class Header {
    /**
     *
     */
    private int CrcCode = 0XABEF0101;

    /**
     * 消息长度
     */
    private int length;
    /**
     * 会话id
     */
    private Long sessionId;
    /**
     * 消息类型
     */
    private byte type;
    /**
     * 消息优先级
     */
    private byte priority;
    /**
     * 附件
     */
    private Map<String, Object> attachment = new HashMap<>();

    public int getCrcCode() {
        return CrcCode;
    }

    public void setCrcCode(int crcCode) {
        CrcCode = crcCode;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public Map<String, Object> getAttachment() {
        return attachment;
    }

    public void setAttachment(Map<String, Object> attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "Header{" +
                "CrcCode=" + CrcCode +
                ", length=" + length +
                ", sessionId=" + sessionId +
                ", type=" + type +
                ", priority=" + priority +
                ", attachment=" + attachment +
                '}';
    }
}
