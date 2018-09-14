package app.client.net.protocol.response;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.ResponseProtocol;
import com.google.protobuf.InvalidProtocolBufferException;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.xb.tcp.proto.ChatMsgProto;
import com.gowild.xbtcp.metadata.pb.BaseBothMsgProto;

/**
 * 新版闲聊
 */
@Protocol(moduleId = SdkMsgType.XB_CLIENT_TYPE, sequenceId = 406, type = ProtocolType.RESPONSE)
public class S_XB_CHAT_V2 extends ResponseProtocol{

    private ChatMsgProto.ChatMsg chatMsg;

    @Override
    public boolean readBinaryData(){
        try {
            chatMsg = ChatMsgProto.ChatMsg.parseFrom(buffer.array());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return true;
    }

    public ChatMsgProto.ChatMsg getChatMsg() {
        return chatMsg;
    }
}
