package app.client.net.protocol.request.sdk.device;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import com.gowild.protocol.Device2SdkTcpProtocol;
import com.gowild.protocol.SdkMsgType;


@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Device2SdkTcpProtocol.SDK_SOCKET_HEART_BEAT_C, type = ProtocolType.REQUSET)
public class C_DEVICE_HEART_BEAT extends RequestProtocol{

    @Override
    public void writeBinaryData(){
    }
}
