package app.client.net.protocol.response.sdk;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.ResponseProtocol;
import com.google.protobuf.InvalidProtocolBufferException;
import com.gowild.sdk.metadata.pb.BaseBothMsgProto;
import com.gowild.sdk.metadata.pb.SdkBothMsgProto;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdk.protocol.Tcp2DeviceProtocol;

@Protocol(moduleId = SdkMsgType.XB_CLIENT_TYPE, sequenceId = Tcp2DeviceProtocol.SMARTHOME_DOCKER_SPEAK_S, type = ProtocolType.RESPONSE)
public class S_DOCKER_SPEAK extends ResponseProtocol{

    BaseBothMsgProto.StringMsg stringMsg;

    @Override
    public boolean readBinaryData(){
        try {
            stringMsg = BaseBothMsgProto.StringMsg.parseFrom(buffer.array());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return true;
    }

    public BaseBothMsgProto.StringMsg getStringMsg() {
        return stringMsg;
    }

    public void setStringMsg(BaseBothMsgProto.StringMsg stringMsg) {
        this.stringMsg = stringMsg;
    }
}
