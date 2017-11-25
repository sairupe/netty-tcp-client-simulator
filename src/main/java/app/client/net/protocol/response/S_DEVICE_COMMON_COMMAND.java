package app.client.net.protocol.response;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.ResponseProtocol;
import com.google.protobuf.InvalidProtocolBufferException;
import com.gowild.protocol.SdkMsgType;
import com.gowild.protocol.SdkTcp2DeviceProtocol;
import com.gowild.sdktcp.metadata.pb.SdkS2SdkMsgProto;

@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = SdkTcp2DeviceProtocol.SDK_SEND_COMMON_COMMAND_S, type = ProtocolType.RESPONSE)
public class S_DEVICE_COMMON_COMMAND extends ResponseProtocol{

    SdkS2SdkMsgProto.PushCommonCommandMsg pushCommonCommandMsg;

    @Override
    public boolean readBinaryData(){
        try {
            pushCommonCommandMsg = SdkS2SdkMsgProto.PushCommonCommandMsg.parseFrom(buffer.array());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return true;
    }

    public SdkS2SdkMsgProto.PushCommonCommandMsg getPushCommonCommandMsg() {
        return pushCommonCommandMsg;
    }
}
