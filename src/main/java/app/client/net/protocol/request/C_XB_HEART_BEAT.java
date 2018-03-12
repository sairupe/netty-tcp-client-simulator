package app.client.net.protocol.request;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import com.gowild.sdk.protocol.SdkMsgType;


@Protocol(moduleId = SdkMsgType.XB_CLIENT_TYPE, sequenceId = 107, type = ProtocolType.REQUSET)
public class C_XB_HEART_BEAT extends RequestProtocol{

    @Override
    public void writeBinaryData(){

    }
}
