package app.client.net.protocol.response.sdk.device;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.ResponseProtocol;
import com.google.protobuf.InvalidProtocolBufferException;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdk.protocol.Tcp2DeviceProtocol;
import com.gowild.sdk.metadata.pb.Tcp2SdkMsgProto;

@Protocol(moduleId = 1, sequenceId = 2756, type = ProtocolType.RESPONSE)
public class S_DEVICE_ATTR_COMMAND extends ResponseProtocol{

    Tcp2SdkMsgProto.PushCommonCommandMsg pushCommonCommandMsg;

    @Override
    public boolean readBinaryData(){
        try {
            pushCommonCommandMsg = Tcp2SdkMsgProto.PushCommonCommandMsg.parseFrom(buffer.array());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Tcp2SdkMsgProto.PushCommonCommandMsg getPushCommonCommandMsg() {
        return pushCommonCommandMsg;
    }
}
