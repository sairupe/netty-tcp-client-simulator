package app.client.net.protocol.request;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import com.gowild.protocol.Device2SdkTcpProtocol;
import com.gowild.protocol.SdkMsgType;
import com.gowild.sdktcp.metadata.pb.BaseBothMsgProto;
import com.gowild.sdktcp.metadata.pb.SdkDeviceBothMsg;


@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Device2SdkTcpProtocol.SDK_SIMULAR_SEMANTIC_COMMAND_C, type = ProtocolType.REQUSET)
public class C_DEVICE_SIMULAR_COMMAND extends RequestProtocol{

    /**
     * JSON命令行
     */
    private String semanticCommand;

    @Override
    public void writeBinaryData(){
        BaseBothMsgProto.StringMsg.Builder build = BaseBothMsgProto.StringMsg.newBuilder();
        build.setValue(semanticCommand);
        byte[] bytes = build.build().toByteArray();
        for(byte b : bytes){
            writeByte(b);
        }
    }

    public void setSemanticCommand(String semanticCommand) {
        this.semanticCommand = semanticCommand;
    }
}
