package app.client.net.protocol.request.sdk.home;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import com.gowild.sdk.protocol.Device2TcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdk.metadata.pb.Sdk2TcpMsgProto;

import java.util.List;


@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Device2TcpProtocol.SDK_DELETE_HOME_C, type = ProtocolType.REQUSET)
public class C_DELETE_HOME_C extends RequestProtocol{

    private List<String> deleteHomeIdList;

    @Override
    public void writeBinaryData(){
        Sdk2TcpMsgProto.SdkDeleteHomeMsg.Builder build = Sdk2TcpMsgProto.SdkDeleteHomeMsg.newBuilder();
        for(String deleteId : deleteHomeIdList){
            build.addHomeIdList(deleteId);
        }
        byte[] bytes = build.build().toByteArray();
        for(byte b : bytes){
            writeByte(b);
        }
    }

    public List<String> getDeleteHomeIdList() {
        return deleteHomeIdList;
    }

    public void setDeleteHomeIdList(List<String> deleteHomeIdList) {
        this.deleteHomeIdList = deleteHomeIdList;
    }
}
