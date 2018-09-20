package app.client.net.protocol.response.plugin;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.ResponseProtocol;
import com.google.protobuf.InvalidProtocolBufferException;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.xbtcp.metadata.pb.DeviceTimingBothMsgProto;

@Protocol(moduleId = SdkMsgType.APP_CLIENT_TYPE, sequenceId = 2108, type = ProtocolType.RESPONSE)
public class S_DEVICE_TIMING_DELETE_APP extends ResponseProtocol{

    private DeviceTimingBothMsgProto.DeleteDeviceTimingMsg deleteDeviceTimingMsg;

    @Override
    public boolean readBinaryData(){
        try {
            deleteDeviceTimingMsg = DeviceTimingBothMsgProto.DeleteDeviceTimingMsg.parseFrom(buffer.array());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return true;
    }

    public DeviceTimingBothMsgProto.DeleteDeviceTimingMsg getDeleteDeviceTimingMsg() {
        return deleteDeviceTimingMsg;
    }
}
