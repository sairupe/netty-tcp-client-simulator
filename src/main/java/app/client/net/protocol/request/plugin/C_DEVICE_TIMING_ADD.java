package app.client.net.protocol.request.plugin;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.xbtcp.metadata.pb.DeviceBothMsg;
import com.gowild.xbtcp.metadata.pb.DeviceTimingAC2SMsgProto;
import com.gowild.xbtcp.metadata.pb.DeviceTimingBothMsgProto;


@Protocol(moduleId = SdkMsgType.APP_CLIENT_TYPE, sequenceId = 1903, type = ProtocolType.REQUSET)
public class C_DEVICE_TIMING_ADD extends RequestProtocol {

    private String pluginMac;

    private long executeTime;

    private boolean isOpen;

    @Override
    public void writeBinaryData() {
        DeviceTimingAC2SMsgProto.AddDeviceTimingMsg.Builder builder = DeviceTimingAC2SMsgProto.AddDeviceTimingMsg.newBuilder();
        builder.setMac(pluginMac);
        builder.setExecuteTime(executeTime);
        if (isOpen) {
            builder.setDeviceOpenStatus(DeviceBothMsg.DeviceOpenStatusEnum.OPEN);
        } else {
            builder.setDeviceOpenStatus(DeviceBothMsg.DeviceOpenStatusEnum.CLOSE);
        }
        byte[] bytes = builder.build().toByteArray();
        writeBytes(bytes);
    }

    public String getPluginMac() {
        return pluginMac;
    }

    public void setPluginMac(String pluginMac) {
        this.pluginMac = pluginMac;
    }

    public long getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(long executeTime) {
        this.executeTime = executeTime;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
