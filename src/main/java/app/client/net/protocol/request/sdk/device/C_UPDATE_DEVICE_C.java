package app.client.net.protocol.request.sdk.device;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import app.client.net.protocol.request.sdk.vo.AddDeviceInfoVo;
import com.gowild.sdk.protocol.Device2SdkTcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdktcp.metadata.pb.SdkUploadMsgProto;

import java.util.List;


@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Device2SdkTcpProtocol.SDK_UPDATE_DEVICE_INFO_C, type = ProtocolType.REQUSET)
public class C_UPDATE_DEVICE_C extends RequestProtocol{

    private List<AddDeviceInfoVo> updateDeviceInfoVoList;

    @Override
    public void writeBinaryData(){

        SdkUploadMsgProto.SdkUpdateDeviceMsg.Builder build = SdkUploadMsgProto.SdkUpdateDeviceMsg.newBuilder();
        for(AddDeviceInfoVo addDeviceInfoVo : updateDeviceInfoVoList){
            SdkUploadMsgProto.SdkUpdateDeviceInfo.Builder second = SdkUploadMsgProto.SdkUpdateDeviceInfo.newBuilder();
            second.setDeviceId(addDeviceInfoVo.getDeviceId());
            second.setDeviceName(addDeviceInfoVo.getDeviceName());
            second.setOpenStatus(addDeviceInfoVo.getOpenStatus());
            second.setOnlineStatus(addDeviceInfoVo.getOnlineStatus());
            second.setDeviceType(addDeviceInfoVo.getDeviceType());
            build.addDeviceList(second.build());
        }
        byte[] bytes = build.build().toByteArray();
        for(byte b : bytes){
            writeByte(b);
        }
    }

    public List<AddDeviceInfoVo> getUpdateDeviceInfoVoList() {
        return updateDeviceInfoVoList;
    }

    public void setUpdateDeviceInfoVoList(List<AddDeviceInfoVo> updateDeviceInfoVoList) {
        this.updateDeviceInfoVoList = updateDeviceInfoVoList;
    }
}
