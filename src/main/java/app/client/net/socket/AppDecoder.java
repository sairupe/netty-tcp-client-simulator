/**
 * Copyright(c) 2014 ShenZhen Gowild Intelligent Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-24  下午3:02:17
 */
package app.client.net.socket;

import app.client.common.CommonConsts;
import app.client.common.ConfigConst;
import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.ResponseProtocol;
import app.client.user.session.UserSession;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
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
         +   * | 123456(4 byte) | (2 byte)     | (1 byte)   | (? byte)         |
         +   * +----------------+--------------+------------+------------------+
         +   * MSG_Length = sizeof(Bytes Array)
         */

        if (in.readableBytes() < 7) {
            return;
        }

        Integer protoId = in.readInt();
        Short msgLength = in.readShort();
        byte tickCount = in.readByte();
        int reableSize = in.readableBytes();
        if (reableSize < msgLength) {
            // 数据长度不足，重置到开头, 等待下次接收, 不然上面read的位置就不对了
            in.readerIndex(0);
            return;
        }

        // 读取数据并解密数据
        int singlePacketgeLength = 7 + msgLength;
        byte[] bodyBytes = new byte[singlePacketgeLength];

        in.getBytes(7, bodyBytes, 0, singlePacketgeLength);// 读取数据结构体完整包,前面关键信息已经读过了不用再读
        in.readerIndex(singlePacketgeLength);// 读索引置为从这个包总长度后开始,既下个包开始读取的位置

        // 直接转换成响应协议
        UserSession userSession = ctx.channel().attr(ConfigConst.USER_SESSION).get();
        ByteBuf bodyBuf = Unpooled.wrappedBuffer(bodyBytes);
        ResponseProtocol response = ProtocolFactory
                .getResponseProtocol(CommonConsts.CLIENT_TYPE_APP, protoId,
                        bodyBuf, userSession);
        out.add(response);
        return;
    }
}
