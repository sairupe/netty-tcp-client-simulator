package app.client.net.protocol.request.sdk.device;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import app.client.net.protocol.request.sdk.vo.AddDeviceInfoVo;
import com.gowild.core.util.StringUtil;
import com.gowild.protocol.Device2SdkTcpProtocol;
import com.gowild.protocol.SdkMsgType;
import com.gowild.sdktcp.metadata.pb.BaseBothMsgProto;
import com.gowild.sdktcp.metadata.pb.SdkUploadMsgProto;

import java.util.List;


@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Device2SdkTcpProtocol.SDK_GET_ALL_DATA_BY_XB_MAC_C, type = ProtocolType.REQUSET)
public class C_GET_ALL_XB_BIND_MASTER extends RequestProtocol{

    private String xbMac;

    @Override
    public void writeBinaryData(){

        BaseBothMsgProto.StringMsg.Builder build = BaseBothMsgProto.StringMsg.newBuilder();
        build.setValue(xbMac);
        byte[] bytes = build.build().toByteArray();
        for(byte b : bytes){
            writeByte(b);
        }
    }

    public String getXbMac() {
        return xbMac;
    }

    public void setXbMac(String xbMac) {
        this.xbMac = xbMac;
    }
}
