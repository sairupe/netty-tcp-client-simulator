package app.client.net.protocol.response;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.ResponseProtocol;
import com.gowild.protocol.SdkMsgType;

@Protocol(moduleId = SdkMsgType.XB_CLIENT_TYPE, sequenceId = 110, type = ProtocolType.RESPONSE)
public class S_XB_HEART_BEAT extends ResponseProtocol{

    @Override
    public boolean readBinaryData(){
        return true;
    }

}
