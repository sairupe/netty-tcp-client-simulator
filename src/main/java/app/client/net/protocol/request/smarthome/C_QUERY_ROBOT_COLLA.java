package app.client.net.protocol.request.smarthome;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.smarthome.metadata.pb.SmartHomeAC2SMsgProto;


@Protocol(moduleId = SdkMsgType.APP_CLIENT_TYPE, sequenceId = 2341, type = ProtocolType.REQUSET)
public class C_QUERY_ROBOT_COLLA extends RequestProtocol{

    private int robotId;

    @Override
    public void writeBinaryData(){
        SmartHomeAC2SMsgProto.SmartHomeQueryRobotCollaMsg.Builder build = SmartHomeAC2SMsgProto.SmartHomeQueryRobotCollaMsg.newBuilder();
        build.setRobotId(robotId);
        writeBytes(build.build().toByteArray());
    }

    public void setRobotId(int robotId) {
        this.robotId = robotId;
    }
}
