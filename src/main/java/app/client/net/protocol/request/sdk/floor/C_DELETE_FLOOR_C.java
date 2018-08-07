package app.client.net.protocol.request.sdk.floor;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import com.gowild.sdk.protocol.Device2TcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdk.metadata.pb.Sdk2TcpMsgProto;

import java.util.List;


@Protocol(moduleId = SdkMsgType.XB_CLIENT_TYPE, sequenceId = Device2TcpProtocol.SDK_DELETE_FLOOR_C, type = ProtocolType.REQUSET)
public class C_DELETE_FLOOR_C extends RequestProtocol{

    private List<String> deleteFloorIdList;

    @Override
    public void writeBinaryData(){
        Sdk2TcpMsgProto.SdkDeleteFloorMsg.Builder build = Sdk2TcpMsgProto.SdkDeleteFloorMsg.newBuilder();
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
