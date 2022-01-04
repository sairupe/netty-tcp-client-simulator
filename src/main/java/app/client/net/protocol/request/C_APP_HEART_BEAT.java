package app.client.net.protocol.request;

import app.client.common.CommonConsts;
import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;


@Protocol(moduleId = CommonConsts.CLIENT_TYPE_APP, sequenceId = 151, type = ProtocolType.REQUSET)
public class C_APP_HEART_BEAT extends RequestProtocol {

    private String token;

    @Override
    public void writeBinaryData() {
        ChannelBuffer buf = ChannelBuffers.dynamicBuffer();

        writeBytes(buf);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
