package app.client.net.protocol.response.smarthome;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.ResponseProtocol;
import com.google.protobuf.InvalidProtocolBufferException;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.smarthome.metadata.pb.SmartHomeS2ACMsgProto;

@Protocol(moduleId = SdkMsgType.APP_CLIENT_TYPE, sequenceId = 2342, type = ProtocolType.RESPONSE)
public class S_QUERY_ROBOT_COLLA_RES extends ResponseProtocol{

    SmartHomeS2ACMsgProto.SmartHomeQueryRobotCollaResMsg queryRobotCollaResMsg;

    @Override
    public boolean readBinaryData(){
        try {
            queryRobotCollaResMsg = SmartHomeS2ACMsgProto.SmartHomeQueryRobotCollaResMsg.parseFrom(buffer.array());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return true;
    }

    public SmartHomeS2ACMsgProto.SmartHomeQueryRobotCollaResMsg getQueryRobotCollaResMsg() {
        return queryRobotCollaResMsg;
    }
}
