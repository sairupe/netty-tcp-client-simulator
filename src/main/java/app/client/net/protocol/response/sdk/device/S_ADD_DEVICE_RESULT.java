package app.client.net.protocol.response.sdk.device;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.ResponseProtocol;
import com.google.protobuf.InvalidProtocolBufferException;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdk.protocol.SdkTcp2DeviceProtocol;
import com.gowild.sdktcp.metadata.pb.SdkBothMsgProto;

@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = SdkTcp2DeviceProtocol.SDK_MASTER_BIND_DEVICES_RESULT_S, type = ProtocolType.RESPONSE)
public class S_ADD_DEVICE_RESULT extends ResponseProtocol{

    SdkBothMsgProto.SdkCommonResponseMsg commonResponseMsg;

    @Override
    public boolean readBinaryData(){
        try {
            commonResponseMsg = SdkBothMsgProto.SdkCommonResponseMsg.parseFrom(buffer.array());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return true;
    }

    public SdkBothMsgProto.SdkCommonResponseMsg getCommonResponseMsg() {
        return commonResponseMsg;
    }
}
