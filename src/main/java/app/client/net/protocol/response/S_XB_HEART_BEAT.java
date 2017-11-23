package app.client.net.protocol.response;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.ResponseProtocol;
import com.google.protobuf.InvalidProtocolBufferException;
import com.gowild.protocol.SdkMsgType;
import com.gowild.protocol.SdkTcp2DeviceProtocol;
import com.gowild.sdktcp.metadata.pb.SdkDeviceBothMsg;

@Protocol(moduleId = SdkMsgType.XB_CLIENT_TYPE, sequenceId = 110, type = ProtocolType.RESPONSE)
public class S_XB_HEART_BEAT extends ResponseProtocol{

    @Override
    public boolean readBinaryData(){
        return true;
    }

}
