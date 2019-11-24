package com.elisland.priprotocol;

/**
 * @Auther: jerry.feng
 * @Date: 2019/6/21
 * @Description:
 */
public enum MessageType {
    LOGIN_REQ((byte) 3),
    LOGIN_RESP((byte) 4),
    HEARTBEAT_REQ((byte)5),
    HEARTBEAT_RESP((byte)6);

    private final byte type;

    MessageType(byte type) {
        this.type = type;
    }

    public static MessageType of(int type) {
        for (MessageType messageType : values()) {
            if (messageType.type == type) {
                return messageType;
            }
        }
        return null;
    }

    public byte getType() {
        return type;
    }
}
