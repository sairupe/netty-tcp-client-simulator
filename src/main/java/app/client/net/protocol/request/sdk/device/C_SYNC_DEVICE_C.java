package app.client.net.protocol.request.sdk.device;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import app.client.net.protocol.request.sdk.vo.AddDeviceInfoVo;
import com.gowild.core.util.StringUtil;
import com.gowild.sdk.protocol.Device2TcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdk.metadata.pb.Sdk2TcpMsgProto;

import java.util.List;


@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Device2TcpProtocol.SDK_SYNC_DEVICE_C, type = ProtocolType.REQUSET)
public class C_SYNC_DEVICE_C extends RequestProtocol{

    private List<AddDeviceInfoVo> addDeviceInfoVoList;

    @Override
    public void writeBinaryData(){

        Sdk2TcpMsgProto.SdkAddDeviceBatchMsg.Builder build = Sdk2TcpMsgProto.SdkAddDeviceBatchMsg.newBuilder();
        for(AddDeviceInfoVo addDeviceInfoVo : addDeviceInfoVoList){
            Sdk2TcpMsgProto.SdkAddDevice.Builder second = Sdk2TcpMsgProto.SdkAddDevice.newBuilder();
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
