package app.client.net.protocol.request.sdk.floor;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import app.client.net.protocol.request.sdk.vo.AddFloorInfoVo;
import com.gowild.sdk.protocol.Device2SdkTcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdktcp.metadata.pb.SdkUploadMsgProto;

import java.util.List;


@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Device2SdkTcpProtocol.SDK_UPDATE_FLOOR_INFO_C, type = ProtocolType.REQUSET)
public class C_UPDATE_FLOOR_C extends RequestProtocol{

    private List<AddFloorInfoVo> updateHomeInfoVoList;

    @Override
    public void writeBinaryData(){

        SdkUploadMsgProto.SdkUpdateFloorMsg.Builder build = SdkUploadMsgProto.SdkUpdateFloorMsg.newBuilder();
        for(AddFloorInfoVo addFloorInfoVo : updateHomeInfoVoList){
            SdkUploadMsgProto.SdkUpdateFloorInfo.Builder second = SdkUploadMsgProto.SdkUpdateFloorInfo.newBuilder();
            second.setFloorId(addFloorInfoVo.getFloorId());
            second.setFloorName(addFloorInfoVo.getFloorName());
            build.addFloorList(second.build());
        }
        byte[] bytes = build.build().toByteArray();
        for(byte b : bytes){
            writeByte(b);
        }
    }

    public List<AddFloorInfoVo> getUpdateHomeInfoVoList() {
        return updateHomeInfoVoList;
    }

    public void setUpdateHomeInfoVoList(List<AddFloorInfoVo> updateHomeInfoVoList) {
        this.updateHomeInfoVoList = updateHomeInfoVoList;
    }
}
