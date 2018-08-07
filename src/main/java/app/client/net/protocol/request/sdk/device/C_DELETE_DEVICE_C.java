package app.client.net.protocol.request.sdk.device;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import com.gowild.sdk.protocol.Device2TcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdk.metadata.pb.Sdk2TcpMsgProto;

import java.util.List;


@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Device2TcpProtocol.SDK_DELETE_MASTER_BIND_DEVICE_C, type = ProtocolType.REQUSET)
public class C_DELETE_DEVICE_C extends RequestProtocol{

    private List<String> deleteDeviceIdList;

    @Override
    public void writeBinaryData(){
        Sdk2TcpMsgProto.SdkDeleteDeviceMsg.Builder build = Sdk2TcpMsgProto.SdkDeleteDeviceMsg.newBuilder();
        for(String deleteId : deleteDeviceIdList){
            build.addDeviceIdList(deleteId);
        }
        byte[] bytes = build.build().toByteArray();
        for(byte b : bytes){
            writeByte(b);
        }
    }

    public List<String> getDeleteDeviceIdList() {
        return deleteDeviceIdList;
    }

    public void setDeleteDeviceIdList(List<String> deleteDeviceIdList) {
        this.deleteDeviceIdList = deleteDeviceIdList;
    }
}
