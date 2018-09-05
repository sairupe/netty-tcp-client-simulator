package app.client.net.protocol.request.smarthome;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import app.client.net.protocol.request.sdk.vo.AddAreaInfoVo;
import com.gowild.core.util.StringUtil;
import com.gowild.sdk.metadata.pb.Sdk2TcpMsgProto;
import com.gowild.sdk.protocol.Device2TcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.smarthome.metadata.pb.SmartHomeAC2SMsgProto;

import java.util.List;


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
