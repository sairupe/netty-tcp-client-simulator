package app.client.net.protocol.request;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import com.gowild.protocol.SdkMsgType;


@Protocol(moduleId = SdkMsgType.APP_CLIENT_TYPE, sequenceId = 103, type = ProtocolType.REQUSET)
public class C_APP_HEART_BEAT extends RequestProtocol{

    @Override
    public void writeBinaryData(){

    }
}
