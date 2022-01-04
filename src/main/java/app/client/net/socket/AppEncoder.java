/**
 * Copyright(c) 2014 ShenZhen Gowild Intelligent Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-24  下午3:02:17
 */
package app.client.net.socket;

import app.client.common.ConfigConst;
import app.client.net.protocol.RequestProtocol;
import app.client.user.session.UserSession;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.jboss.netty.buffer.ChannelBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Netty编码器-密文.
 *
 * @author dream.xie
 */
public class AppEncoder extends MessageToByteEncoder<RequestProtocol> {

    private static final Logger logger = LoggerFactory
            .getLogger(AppEncoder.class);

    @Override
    protected void encode(final ChannelHandlerContext ctx, final RequestProtocol requestProtocol, final ByteBuf out) throws Exception {
        /**
         * 协议包结构
         * +----------------+--------------+-------------+-----------------+
         * | PROTO ID       | MSG_Length   | Tick Count | Bytes Array      |
         * | 0x7FFE(4 byte) | (2 byte)     | (1 byte)   | (? byte)         |
         * +----------------+--------------+------------+------------------+
         *
         * MSG_Length = sizeof(Bytes Array)
         */
        // 发送协议数
        UserSession userSession = ctx.channel().attr(ConfigConst.USER_SESSION).get();
        Long tick = userSession.getTick();
        tick++;
        userSession.setTick(tick);
        byte tickCount = (byte) (tick.longValue() % 128);
        // 写入数据长度
        int protoId = requestProtocol.getModuleId();
        ChannelBuffer buffer = requestProtocol.getWriteBuf();
        int totalSize = buffer.writerIndex();
        short msgLength = (short) (totalSize - 7);
        buffer.writerIndex(0);// 初始化到头部
        buffer.writeInt(protoId);
        buffer.writeShort(msgLength);
        buffer.writeByte(tickCount);
        // 分配并写入到输出流
        byte[] outBytes = new byte[totalSize];
        buffer.readBytes(outBytes);
        out.writeBytes(outBytes);
    }
}
