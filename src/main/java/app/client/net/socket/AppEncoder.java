/**
 * Copyright(c) 2014 ShenZhen Gowild Intelligent Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-24  下午3:02:17
 */
package app.client.net.socket;

import app.client.common.CommonConsts;
import app.client.common.ConfigConst;
import app.client.data.StatisticHolder;
import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.RequestProtocol;
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
        // 写入数据长度
        ChannelBuffer buffer = requestProtocol.getBuffer();
        int size = buffer.writerIndex();
        buffer.writerIndex(0);
        buffer.writeShort(ProtocolFactory.HEADER);
        buffer.writeShort(size);
        buffer.writerIndex(size);
        // 分配并写入到输出流
        byte[] outBytes = new byte[size];
        buffer.readBytes(outBytes);
        out.writeBytes(outBytes);
    }

    /**
     * 获取当前加解密密钥
     *
     * @return
     */
    private static int[] getKey(final ChannelHandlerContext ctx) {
        return ctx.channel().attr(ConfigConst.ENCRYPTION_KEY).get();
    }
}
