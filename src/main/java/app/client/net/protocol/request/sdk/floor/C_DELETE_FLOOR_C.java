package app.client.net.protocol.request.sdk.floor;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import com.gowild.sdk.protocol.Device2SdkTcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdktcp.metadata.pb.SdkUploadMsgProto;

import java.util.List;


@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Device2SdkTcpProtocol.SDK_DELETE_FLOOR_INFO_C, type = ProtocolType.REQUSET)
public class C_DELETE_FLOOR_C extends RequestProtocol{

    private List<String> deleteFloorIdList;

    @Override
    public void writeBinaryData(){
        SdkUploadMsgProto.SdkDeleteFloorMsg.Builder build = SdkUploadMsgProto.SdkDeleteFloorMsg.newBuilder();
        for(String deleteId : deleteFloorIdList){
            build.addFloorIdList(deleteId);
        }
        byte[] bytes = build.build().toByteArray();
        for(byte b : bytes){
            writeByte(b);
        }
    }

    public List<String> getDeleteFloorIdList() {
        return deleteFloorIdList;
    }

    public void setDeleteFloorIdList(List<String> deleteFloorIdList) {
        this.deleteFloorIdList = deleteFloorIdList;
    }
}
