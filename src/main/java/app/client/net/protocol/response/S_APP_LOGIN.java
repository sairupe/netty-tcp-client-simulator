package app.client.net.protocol.response;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.ResponseProtocol;
import com.google.protobuf.InvalidProtocolBufferException;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.xbtcp.metadata.pb.BaseBothMsgProto;

@Protocol(moduleId = SdkMsgType.APP_CLIENT_TYPE, sequenceId = 152, type = ProtocolType.RESPONSE)
public class S_APP_LOGIN extends ResponseProtocol{

    private BaseBothMsgProto.BaseResponse loginResult;

    @Override
    public boolean readBinaryData(){
        try {
            loginResult = BaseBothMsgProto.BaseResponse.parseFrom(buffer.array());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return true;
    }

    public BaseBothMsgProto.BaseResponse getLoginResult() {
        return loginResult;
    }
}
