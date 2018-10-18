package app.client.net.protocol.response;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.ResponseProtocol;
import com.google.protobuf.InvalidProtocolBufferException;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.xb.tcp.proto.ChatMsgProto;
import com.gowild.xbtcp.metadata.pb.ChatS2XCMsgProto;

/**
 * 科大请求完成
 */
@Protocol(moduleId = SdkMsgType.XB_CLIENT_TYPE, sequenceId = 410, type = ProtocolType.RESPONSE)
public class S_XB_REV_KEDA extends ResponseProtocol{

    private ChatS2XCMsgProto.SendResponseForReceivedKeDa sendResponseForReceivedKeDa;

    @Override
    public boolean readBinaryData(){
        try {
            sendResponseForReceivedKeDa = ChatS2XCMsgProto.SendResponseForReceivedKeDa.parseFrom(buffer.array());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return true;
    }

    public ChatS2XCMsgProto.SendResponseForReceivedKeDa getSendResponseForReceivedKeDa() {
        return sendResponseForReceivedKeDa;
    }
}
