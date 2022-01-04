package app.client.net.protocol.response;

import app.client.common.CommonConsts;
import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.ResponseProtocol;
import io.netty.buffer.ByteBuf;
import lombok.Data;
import org.jboss.netty.buffer.ChannelBuffer;

@Data
@Protocol(moduleId = CommonConsts.CLIENT_TYPE_APP, sequenceId = 11001, type = ProtocolType.RESPONSE)
public class S_APP_LOGIN extends ResponseProtocol {

    private Long userId;

    private Short code;

    @Override
    public boolean readBinaryData() {
        ByteBuf bodyBuf = getBodyBuf();
        userId = bodyBuf.readLong();
        code = bodyBuf.readShort();
        return true;
    }

}
