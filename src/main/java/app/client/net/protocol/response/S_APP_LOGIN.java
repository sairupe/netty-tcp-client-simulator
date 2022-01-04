package app.client.net.protocol.response;

import app.client.common.CommonConsts;
import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.ResponseProtocol;
import lombok.Data;

@Data
@Protocol(moduleId = CommonConsts.CLIENT_TYPE_APP, sequenceId = 152, type = ProtocolType.RESPONSE)
public class S_APP_LOGIN extends ResponseProtocol{

    private Long userId;

    private Short code;

    @Override
    public boolean readBinaryData(){
        return true;
    }

}
