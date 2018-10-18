package app.client.net.socket;
import java.nio.ByteBuffer;

public final class Message {
    public static final short HEAD_SIZE = 7;
    public static final short HEADER = 32766;
    private short code;
    private byte type;
    private byte[] bodyData = new byte[0];

    private Message() {
    }

    public static Message createMessage(byte type, short code) {
        Message message = new Message();
        message.type = type;
        message.code = code;
        return message;
    }

    public static Message parse(byte[] dataBytes) {
        if(dataBytes.length < 7) {
            return null;
        } else {
            Message message = new Message();
            ByteBuffer byteBuffer = ByteBuffer.wrap(dataBytes);
            byteBuffer.position(4);
            message.code = byteBuffer.getShort();
            message.type = byteBuffer.get();
            int bodyLen = dataBytes.length - 7;
            if(dataBytes.length > 7) {
                message.bodyData = new byte[bodyLen];
                byteBuffer.get(message.bodyData, 0, bodyLen);
            }

            return message;
        }
    }

    public ByteBuffer toByteBuffer() {
        short len = 7;
        if(this.bodyData != null) {
            len += (short)this.bodyData.length;
        }

        if(len <= 0) {
            throw new IllegalArgumentException(String.format("发送协议号为[%d]的包,长度小于等于0[%d]，长度=包头长度[10]+body长度[%d]", new Object[]{Short.valueOf(this.code), Short.valueOf(len), Short.valueOf((short)this.bodyData.length)}));
        } else {
            ByteBuffer buff = ByteBuffer.allocate(len);
            buff.putShort((short)32766);
            buff.putShort(len);
            buff.putShort(this.code);
            buff.put(this.type);
            if(this.bodyData != null) {
                buff.put(this.bodyData);
            }

            buff.flip();
            return buff;
        }
    }

    public byte[] getBody() {
        return this.bodyData;
    }

    public void setBody(byte[] bytes) {
        this.bodyData = bytes;
    }

    public byte getType() {
        return this.type;
    }

    public short getCode() {
        return this.code;
    }

    public int getBodyLength() {
        return this.bodyData != null?this.bodyData.length:0;
    }

    public String toString() {
        return "Message [code=" + this.code + ", type=" + this.type + "]";
    }
}