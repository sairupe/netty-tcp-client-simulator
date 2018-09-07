/**
 * Copyright(c) 2014 ShenZhen Gowild Intelligent Technology Co., Ltd.
 * All rights reserved.
 * Created on  2014-2-24  下午3:02:17
 */
package app.client.net.socket;

import app.client.data.StatisticHolder;
import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.RequestProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.jboss.netty.buffer.ChannelBuffer;


/**
 * Netty编码器-密文.
 *
 * @author dream.xie
 */
public class GowildEncoder extends MessageToByteEncoder<RequestProtocol> {

    @Override
    protected void encode(final ChannelHandlerContext ctx, final RequestProtocol requestProtocol, final ByteBuf out) throws Exception {
        // 写入数据长度
        ChannelBuffer buffer = requestProtocol.getBuffer();
        int size = buffer.writerIndex();
        buffer.writerIndex(0);
        buffer.writeShort(ProtocolFactory.HEADER);
        buffer.writeShort(size);
        buffer.writerIndex(size);
        // 若存在不同线程给同一玩家发送数据的情况，因此加密过程需要同步处理
        byte[] plainText = new byte[size];
        buffer.readBytes(plainText);
//        System.out.println("plainText | " + Arrays.toString(plainText));
        //获取key
        int[] encryptKey = getKey(ctx);
        //加密过程
        byte[] cipherText = SocketUtil.encode(plainText, encryptKey);
//        System.out.println("cipherText | " + Arrays.toString(cipherText));
        out.writeBytes(cipherText);
        // 记录登录
        if (requestProtocol.getSequenceId() == 151) {
            int moduleId = requestProtocol.getModuleId();
            if (moduleId == SdkMsgType.APP_CLIENT_TYPE) {
                StatisticHolder.incAppSendLoginCount();
            } else if (moduleId == SdkMsgType.XB_CLIENT_TYPE) {
                StatisticHolder.incRobotSendLoginCount();
            }
        }
    }

    /**
     * 获取当前加解密密钥
     *
     * @return
     */
    private static int[] getKey(final ChannelHandlerContext ctx) {
        return ctx.channel().attr(GowildHandler.ENCRYPTION_KEY).get();
    }
}
