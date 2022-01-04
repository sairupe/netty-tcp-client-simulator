/**
 * Copyright(c) 2014 ShenZhen Gowild Intelligent Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-24  下午3:02:17
 */
package app.client.net.socket;

import app.client.common.ConfigConst;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * Netty解码器-密文.
 *
 * @author dream.xie
 */
@Slf4j
public class AppDecoder extends ByteToMessageDecoder {

    /**
     * 解密
     */
    @Override
    protected void decode(final ChannelHandlerContext ctx, final ByteBuf in, final List<Object> out) throws Exception {
        /**
         * 协议包结构
         -   * +----------------+--------------+-----------+----------+-------------------------+
         -   * | HEADER         | Total_Length | Code      | Type     | ProtoBuffer Bytes Array |
         -   * | 0x7FFE(2 byte) | (2 byte)     | (2 byte)  | (1 byte) | ...                     |
         -   * +----------------+--------------+-----------+----------+-------------------------+
         -   * Total Length = sizeof(HEADER) + sizeof(Total_Length) + sizeof(Code) + sizeOf(Type) + sizeOf(ProtoBuffer Bytes Array)
         -
         +   * +----------------+--------------+-------------+-----------------+
         +   * | PROTO ID       | MSG_Length   | Tick Count | Bytes Array      |
         +   * | 0x7FFE(4 byte) | (2 byte)     | (1 byte)   | (? byte)         |
         +   * +----------------+--------------+------------+------------------+
         +   * MSG_Length = sizeof(Bytes Array)
         */

        if (in.readableBytes() < 4) {
            return;
        }

        int header;
        int packetLength;
        int[] decryptKey = getKey(ctx);
        int cipherByte1;
        int cipherByte2;

        // 此处4字节头部的解码使用直接解码形式，规避频繁的对象创建
        int plainByte1;
        int plainByte2;
        int key;

        // 解密两字节header
        cipherByte1 = in.readByte() & 0xff;
        key = decryptKey[0];
        plainByte1 = (cipherByte1 ^ key) & 0xff;

        cipherByte2 = in.readByte() & 0xff;
        key = (decryptKey[1] ^ cipherByte1);
        plainByte2 = ((cipherByte2 - cipherByte1) ^ key) & 0xff;

        header = ((plainByte1 << 8) + plainByte2);
        // 两字节length,没有加密
        packetLength = in.readShort();
        // 预解密长度信息成功，回溯位置
        in.readerIndex(in.readerIndex() - 4);
        //如果不是标识头，发送给客户端说，断开连接
        if (header != Message.HEADER || packetLength < Message.HEAD_SIZE) {
            // 数据包长度错误，断开连接
            InetSocketAddress socketAddr = (InetSocketAddress) ctx.channel().remoteAddress();
            String ip = socketAddr.getAddress().getHostAddress();
            int port = socketAddr.getPort();
            log.error("密文：IP:{}，PORT:{}，接收Header:{}，固定Header:{}." +
                    "发送的消息头不对，断开连接.", ip, port, header, Message.HEAD_SIZE);
            ctx.close();
            return;
        }

        if (in.readableBytes() < packetLength) {
            // 数据长度不足，等待下次接收
            return;
        }

        // 读取数据并解密数据
        byte[] data = new byte[packetLength];
        in.getBytes(in.readerIndex(), data, 0, packetLength);
        in.readerIndex(in.readerIndex() + packetLength);
        Message packet = Message.parse(data);
        if (packet != null) {
            out.add(packet);
        }
        return;
    }

    /**
     * 获取当前加解密密钥
     *
     * @return
     */
    private static int[] getKey(final ChannelHandlerContext ctx) {
        return ctx.channel().attr(ConfigConst.DECRYPTION_KEY).get();
    }
}
