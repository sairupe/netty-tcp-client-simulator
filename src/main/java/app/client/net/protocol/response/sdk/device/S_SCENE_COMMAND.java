package app.client.net.protocol.response.sdk.device;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.ResponseProtocol;
import com.google.protobuf.InvalidProtocolBufferException;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdk.protocol.Tcp2DeviceProtocol;
import com.gowild.sdk.metadata.pb.Tcp2SdkMsgProto;

@Protocol(moduleId = 1, sequenceId = 2760, type = ProtocolType.RESPONSE)
public class S_SCENE_COMMAND extends ResponseProtocol{

    Tcp2SdkMsgProto.SdkSceneCommandMsg sdkSceneCommandMsg;

    @Override
    public boolean readBinaryData(){
        try {
            sdkSceneCommandMsg = Tcp2SdkMsgProto.SdkSceneCommandMsg.parseFrom(buffer.array());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Tcp2SdkMsgProto.SdkSceneCommandMsg getSdkSceneCommandMsg() {
        return sdkSceneCommandMsg;
    }
}
