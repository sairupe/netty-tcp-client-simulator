package app.client.net.protocol.request;

import app.client.common.CommonConsts;
import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import lombok.Data;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

@Data
@Protocol(moduleId = CommonConsts.CLIENT_TYPE_APP, sequenceId = 151, type = ProtocolType.REQUSET)
public class C_APP_LOGIN extends RequestProtocol {

    private Integer time;
    private Integer server_id;
    private String user_name;
    private String platform_id;
    private String channel_id;
    private String sub_channel_id;
    private Integer infant;
    private String device_imei;
    private String device_id;
    private String device_type;
    private Byte system_type;
    private String sign;

    @Override
    public void writeBinaryData() {
        ChannelBuffer buf = ChannelBuffers.dynamicBuffer();

        writeBytes(buf);
    }
}
