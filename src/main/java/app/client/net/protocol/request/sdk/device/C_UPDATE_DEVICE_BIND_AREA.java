package app.client.net.protocol.request.sdk.device;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import app.client.net.protocol.request.sdk.vo.UpdateDeviceBindArea;
import com.gowild.core.util.StringUtil;
import com.gowild.sdk.protocol.Device2SdkTcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdktcp.metadata.pb.SdkUploadMsgProto;

import java.util.List;


@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Device2SdkTcpProtocol.SDK_UPDATE_DEVICE_BIND_AREA_C, type = ProtocolType.REQUSET)
public class C_UPDATE_DEVICE_BIND_AREA extends RequestProtocol{

    private List<UpdateDeviceBindArea> updateDeviceBindAreaList;

    @Override
    public void writeBinaryData(){
        SdkUploadMsgProto.SdkUpdateDeviceBindAreaMsg.Builder build = SdkUploadMsgProto.SdkUpdateDeviceBindAreaMsg.newBuilder();
        for(UpdateDeviceBindArea updateDeviceBindArea : updateDeviceBindAreaList){
            SdkUploadMsgProto.SdkUpdateDeviceAreaInfo.Builder second = SdkUploadMsgProto.SdkUpdateDeviceAreaInfo.newBuilder();
            second.setDeviceId(updateDeviceBindArea.getDeviceId());
            String areaId = updateDeviceBindArea.getAreaId();
            if(!StringUtil.isNullOrEmpty(areaId)){
                second.setAreaId(areaId);
            }
            build.addDeviceAreaList(second.build());
        }
        byte[] bytes = build.build().toByteArray();
        for(byte b : bytes){
            writeByte(b);
        }
    }

    public List<UpdateDeviceBindArea> getUpdateDeviceBindAreaList() {
        return updateDeviceBindAreaList;
    }

    public void setUpdateDeviceBindAreaList(List<UpdateDeviceBindArea> updateDeviceBindAreaList) {
        this.updateDeviceBindAreaList = updateDeviceBindAreaList;
    }
}
