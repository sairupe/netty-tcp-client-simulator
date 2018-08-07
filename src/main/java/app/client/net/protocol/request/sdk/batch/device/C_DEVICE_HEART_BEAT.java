package app.client.net.protocol.request.sdk.batch.device;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import com.gowild.sdk.protocol.Device2TcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;


@Protocol(moduleId = SdkMsgType.XB_CLIENT_TYPE, sequenceId = Device2TcpProtocol.SDK_SOCKET_HEART_BEAT_C, type = ProtocolType.REQUSET)
public class C_DEVICE_HEART_BEAT extends RequestProtocol{

    @Override
    public void writeBinaryData(){
    }
}
