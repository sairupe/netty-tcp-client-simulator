package app.client.net.protocol.response;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.ResponseProtocol;
import com.gowild.sdk.protocol.SdkMsgType;

@Protocol(moduleId = SdkMsgType.APP_CLIENT_TYPE, sequenceId = 106, type = ProtocolType.RESPONSE)
public class S_APP_HEART_BEAT extends ResponseProtocol{

    @Override
    public boolean readBinaryData(){
        return true;
    }
}
