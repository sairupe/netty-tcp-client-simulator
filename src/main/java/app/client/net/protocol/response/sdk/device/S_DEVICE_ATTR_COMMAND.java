package app.client.net.protocol.response.sdk.device;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.ResponseProtocol;
import com.google.protobuf.InvalidProtocolBufferException;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdk.protocol.SdkTcp2DeviceProtocol;
import com.gowild.sdktcp.metadata.pb.SdkDownloadMsgProto;

@Protocol(moduleId = 1, sequenceId = 2756, type = ProtocolType.RESPONSE)
public class S_DEVICE_ATTR_COMMAND extends ResponseProtocol{

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
