package app.client.net.protocol.request.plugin;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.xbtcp.metadata.pb.DeviceTimingBothMsgProto;
import com.gowild.xbtcp.metadata.pb.MiscAC2SMsgProto;


@Protocol(moduleId = SdkMsgType.XB_CLIENT_TYPE, sequenceId = 2105, type = ProtocolType.REQUSET)
public class C_DEVICE_TIMING_EXECUTED extends RequestProtocol {

    private int timingId;

    private String xbMac;

    @Override
    public void writeBinaryData() {
        DeviceTimingBothMsgProto.DeleteDeviceTimingMsg.Builder builder = DeviceTimingBothMsgProto.DeleteDeviceTimingMsg.newBuilder();
        builder.setTimingId(timingId);
        builder.setMac(xbMac);
        byte[] bytes = builder.build().toByteArray();
        writeBytes(bytes);
    }

    public int getTimingId() {
        return timingId;
    }

    public void setTimingId(int timingId) {
        this.timingId = timingId;
    }

    public String getXbMac() {
        return xbMac;
    }

    public void setXbMac(String xbMac) {
        this.xbMac = xbMac;
    }
}
