package app.client.net.protocol.request.sdk.batch.device;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import app.client.net.protocol.request.sdk.vo.UpdateDeviceBindArea;
import com.gowild.core.util.StringUtil;
import com.gowild.sdk.protocol.Device2TcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdk.metadata.pb.Sdk2TcpMsgProto;

import java.util.List;


@Protocol(moduleId = SdkMsgType.XB_CLIENT_TYPE, sequenceId = Device2TcpProtocol.SDK_UPDATE_DEVICE_BIND_AREA_BATCH_C, type = ProtocolType.REQUSET)
public class C_UPDATE_DEVICE_BIND_AREA_BATCH extends RequestProtocol{

    private List<UpdateDeviceBindArea> updateDeviceBindAreaList;

    @Override
    public void writeBinaryData(){
        Sdk2TcpMsgProto.SdkUpdateDeviceBindAreaBatchMsg.Builder build = Sdk2TcpMsgProto.SdkUpdateDeviceBindAreaBatchMsg.newBuilder();
        for(UpdateDeviceBindArea updateDeviceBindArea : updateDeviceBindAreaList){
            Sdk2TcpMsgProto.SdkUpdateDeviceArea.Builder second = Sdk2TcpMsgProto.SdkUpdateDeviceArea.newBuilder();
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
