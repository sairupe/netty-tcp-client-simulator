package app.client.net.protocol.request.sdk.home;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import com.gowild.sdk.protocol.Device2SdkTcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdktcp.metadata.pb.SdkUploadMsgProto;

import java.util.List;


@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Device2SdkTcpProtocol.SDK_DELETE_HOME_INFO_C, type = ProtocolType.REQUSET)
public class C_DELETE_HOME_C extends RequestProtocol{

    private List<String> deleteHomeIdList;

    @Override
    public void writeBinaryData(){
        SdkUploadMsgProto.SdkDeleteHomeMsg.Builder build = SdkUploadMsgProto.SdkDeleteHomeMsg.newBuilder();
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
