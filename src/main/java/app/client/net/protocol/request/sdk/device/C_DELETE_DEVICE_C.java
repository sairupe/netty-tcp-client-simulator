package app.client.net.protocol.request.sdk.device;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import com.gowild.sdk.protocol.Device2SdkTcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdktcp.metadata.pb.SdkUploadMsgProto;

import java.util.List;


@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Device2SdkTcpProtocol.SDK_DELETE_MASTER_BIND_DEVICE_C, type = ProtocolType.REQUSET)
public class C_DELETE_DEVICE_C extends RequestProtocol{

    private List<String> deleteDeviceIdList;

    @Override
    public void writeBinaryData(){
        SdkUploadMsgProto.SdkDeleteDeviceMsg.Builder build = SdkUploadMsgProto.SdkDeleteDeviceMsg.newBuilder();
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
