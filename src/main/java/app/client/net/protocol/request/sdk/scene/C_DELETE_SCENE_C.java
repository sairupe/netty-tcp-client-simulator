package app.client.net.protocol.request.sdk.scene;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import com.gowild.protocol.Device2SdkTcpProtocol;
import com.gowild.protocol.SdkMsgType;
import com.gowild.sdktcp.metadata.pb.SdkUploadMsgProto;

import java.util.List;


@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Device2SdkTcpProtocol.SDK_DELETE_SCENE_INFO_C, type = ProtocolType.REQUSET)
public class C_DELETE_SCENE_C extends RequestProtocol{

    private List<String> deleteAreaIdList;

    @Override
    public void writeBinaryData(){
        SdkUploadMsgProto.SdkDeleteAreaMsg.Builder build = SdkUploadMsgProto.SdkDeleteAreaMsg.newBuilder();
        for(String deleteId : deleteAreaIdList){
            build.addAreaIdList(deleteId);
        }
        byte[] bytes = build.build().toByteArray();
        for(byte b : bytes){
            writeByte(b);
        }
    }

    public List<String> getDeleteAreaIdList() {
        return deleteAreaIdList;
    }

    public void setDeleteAreaIdList(List<String> deleteAreaIdList) {
        this.deleteAreaIdList = deleteAreaIdList;
    }
}
