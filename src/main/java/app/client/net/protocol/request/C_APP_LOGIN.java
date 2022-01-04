package app.client.net.protocol.request;

import app.client.common.CommonConsts;
import app.client.common.ConfigConst;
import app.client.common.ProtocolUtils;
import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import app.client.utils.MD5Utils;
import lombok.Data;
import org.jboss.netty.buffer.ChannelBuffer;

@Data
@Protocol(moduleId = CommonConsts.CLIENT_TYPE_APP, sequenceId = 11000, type = ProtocolType.REQUSET)
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
        ChannelBuffer buf = this.getWriteBuf();
        buf.writeInt(time);
        buf.writeInt(server_id);
        ProtocolUtils.writeString(buf, user_name);
        ProtocolUtils.writeString(buf, platform_id);
        ProtocolUtils.writeString(buf, channel_id);
        ProtocolUtils.writeString(buf, sub_channel_id);
        buf.writeInt(infant);
        ProtocolUtils.writeString(buf, device_imei);
        ProtocolUtils.writeString(buf, device_id);
        ProtocolUtils.writeString(buf, device_type);
        buf.writeByte(system_type);
        String sign = MD5Utils.MD5(platform_id + user_name + "2" + time + ConfigConst.MD5_SIGN_SALT);
        ProtocolUtils.writeString(buf, sign);
    }
}
