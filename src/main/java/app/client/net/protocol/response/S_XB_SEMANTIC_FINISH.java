package app.client.net.protocol.response;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.ResponseProtocol;
import com.google.protobuf.InvalidProtocolBufferException;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.xbtcp.metadata.pb.ChatS2XCMsgProto;

/**
 * 语义处理完成
 */
@Protocol(moduleId = SdkMsgType.XB_CLIENT_TYPE, sequenceId = 412, type = ProtocolType.RESPONSE)
public class S_XB_SEMANTIC_FINISH extends ResponseProtocol{

    private ChatS2XCMsgProto.SemanticComplete semanticComplete;

    @Override
    public boolean readBinaryData(){
        try {
            semanticComplete = ChatS2XCMsgProto.SemanticComplete.parseFrom(buffer.array());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return true;
    }

    public ChatS2XCMsgProto.SemanticComplete getSemanticComplete() {
        return semanticComplete;
    }
}
