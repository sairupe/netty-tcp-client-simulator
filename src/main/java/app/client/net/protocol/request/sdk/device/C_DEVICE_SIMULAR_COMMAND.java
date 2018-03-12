package app.client.net.protocol.request.sdk.device;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdk.protocol.XbTcp2SdkTcpProtocol;
import com.gowild.sdktcp.metadata.pb.BaseBothMsgProto;


@Protocol(moduleId = SdkMsgType.XB_CLIENT_TYPE, sequenceId = XbTcp2SdkTcpProtocol.SDK_RECEIVED_STATE_CONTROL_COMMAND, type = ProtocolType.REQUSET)
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
