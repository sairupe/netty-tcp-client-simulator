package app.client.net.protocol.request.sdk.device;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import app.client.net.protocol.request.sdk.vo.AddDeviceInfoVo;
import com.gowild.core.util.StringUtil;
import com.gowild.protocol.Device2SdkTcpProtocol;
import com.gowild.protocol.SdkMsgType;
import com.gowild.sdktcp.metadata.pb.SdkUploadMsgProto;

import java.util.List;


@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Device2SdkTcpProtocol.SDK_SYNC_DEVICE_C, type = ProtocolType.REQUSET)
public class C_SYNC_DEVICE_C extends RequestProtocol{

    private List<AddDeviceInfoVo> addDeviceInfoVoList;

    @Override
    public void writeBinaryData(){

        SdkUploadMsgProto.SdkAddDeviceMsg.Builder build = SdkUploadMsgProto.SdkAddDeviceMsg.newBuilder();
        for(AddDeviceInfoVo addDeviceInfoVo : addDeviceInfoVoList){
            SdkUploadMsgProto.SdkAddDeviceInfo.Builder second = SdkUploadMsgProto.SdkAddDeviceInfo.newBuilder();
            second.setDeviceId(addDeviceInfoVo.getDeviceId());
            second.setDeviceName(addDeviceInfoVo.getDeviceName());
            second.setOpenStatus(addDeviceInfoVo.getOpenStatus());
            second.setOnlineStatus(addDeviceInfoVo.getOnlineStatus());
            second.setDeviceType(addDeviceInfoVo.getDeviceType());
            String areaId = addDeviceInfoVo.getAreaId();
            if(!StringUtil.isNullOrEmpty(areaId)){
                second.setAreaId(areaId);
            }
            String sceneId = addDeviceInfoVo.getSceneId();
            if(!StringUtil.isNullOrEmpty(sceneId)){
                second.setSceneId(addDeviceInfoVo.getSceneId());
            }
            build.addDeviceList(second.build());
        }
        byte[] bytes = build.build().toByteArray();
        for(byte b : bytes){
            writeByte(b);
        }
    }

    public List<AddDeviceInfoVo> getAddDeviceInfoVoList() {
        return addDeviceInfoVoList;
    }

    public void setAddDeviceInfoVoList(List<AddDeviceInfoVo> addDeviceInfoVoList) {
        this.addDeviceInfoVoList = addDeviceInfoVoList;
    }
}
