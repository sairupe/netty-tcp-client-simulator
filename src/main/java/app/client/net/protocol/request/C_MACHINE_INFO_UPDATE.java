package app.client.net.protocol.request;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.xbtcp.metadata.pb.MachineBothMsgProto;

/**
 * Created by zh on 2017/12/12.
 */
@Protocol(moduleId = SdkMsgType.XB_CLIENT_TYPE, sequenceId = 203, type = ProtocolType.REQUSET)
public class C_MACHINE_INFO_UPDATE extends RequestProtocol {
    /**
     * 机器电量
     */
    private byte robotPower;

    @Override
    public void writeBinaryData() {
        MachineBothMsgProto.MachineInfoMsg.Builder builder = MachineBothMsgProto.MachineInfoMsg.newBuilder();
        builder.setRobotPower(robotPower);
        writeBytes(builder.build().toByteArray());
    }

    public void setRobotPower(byte robotPower) {
        this.robotPower = robotPower;
    }
}
