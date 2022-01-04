package app.client.net.protocol.response;

import app.client.common.CommonConsts;
import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.ResponseProtocol;

@Protocol(moduleId = CommonConsts.CLIENT_TYPE_APP, sequenceId = 106, type = ProtocolType.RESPONSE)
public class S_APP_HEART_BEAT extends ResponseProtocol{

    @Override
    public boolean readBinaryData(){
        return true;
    }
}
