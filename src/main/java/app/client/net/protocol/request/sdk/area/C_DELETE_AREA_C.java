package app.client.net.protocol.request.sdk.area;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import com.gowild.sdk.protocol.Device2TcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdk.metadata.pb.Sdk2TcpMsgProto;

import java.util.List;


@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Device2TcpProtocol.SDK_DELETE_AREA_C, type = ProtocolType.REQUSET)
public class C_DELETE_AREA_C extends RequestProtocol{

    private List<String> deleteAreaIdList;

    @Override
    public void writeBinaryData(){
        Sdk2TcpMsgProto.SdkDeleteAreaMsg.Builder build = Sdk2TcpMsgProto.SdkDeleteAreaMsg.newBuilder();
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
