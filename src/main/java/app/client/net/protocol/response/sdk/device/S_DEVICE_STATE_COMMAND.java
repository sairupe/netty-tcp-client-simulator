package app.client.net.protocol.response.sdk.device;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.ResponseProtocol;
import com.google.protobuf.InvalidProtocolBufferException;
import com.gowild.protocol.SdkMsgType;
import com.gowild.protocol.SdkTcp2DeviceProtocol;
import com.gowild.sdktcp.metadata.pb.SdkDownloadMsgProto;

@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = SdkTcp2DeviceProtocol.SDK_PUSH_STATE_COMMAND_S, type = ProtocolType.RESPONSE)
public class S_DEVICE_STATE_COMMAND extends ResponseProtocol{

    SdkDownloadMsgProto.PushCommonCommandMsg pushCommonCommandMsg;

    @Override
    public boolean readBinaryData(){
        try {
            pushCommonCommandMsg = SdkDownloadMsgProto.PushCommonCommandMsg.parseFrom(buffer.array());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return true;
    }

    public SdkDownloadMsgProto.PushCommonCommandMsg getPushCommonCommandMsg() {
        return pushCommonCommandMsg;
    }
}
