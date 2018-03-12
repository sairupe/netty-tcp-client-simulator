package app.client.net.protocol.response.sdk.device;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.ResponseProtocol;
import com.google.protobuf.InvalidProtocolBufferException;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdk.protocol.SdkTcp2DeviceProtocol;
import com.gowild.sdktcp.metadata.pb.BaseBothMsgProto;


@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = SdkTcp2DeviceProtocol.SDK_GET_ALL_DATA_BY_XB_MAC_RESULT_S, type = ProtocolType.RESPONSE)
public class S_GET_ALL_XB_BIND_MASTER extends ResponseProtocol {

    BaseBothMsgProto.StringMsg masterInfos;

    @Override
    public boolean readBinaryData() {
        try {
            masterInfos = BaseBothMsgProto.StringMsg.parseFrom(buffer.array());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return true;
    }

    public BaseBothMsgProto.StringMsg getMasterInfos() {
        return masterInfos;
    }
}
