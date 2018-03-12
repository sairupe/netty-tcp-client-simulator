package app.client.net.protocol.request.sdk.area;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import app.client.net.protocol.request.sdk.vo.AddAreaInfoVo;
import com.gowild.sdk.protocol.Device2SdkTcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdktcp.metadata.pb.SdkUploadMsgProto;

import java.util.List;


@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Device2SdkTcpProtocol.SDK_UPDATE_AREA_INFO_C, type = ProtocolType.REQUSET)
public class C_UPDATE_AREA_C extends RequestProtocol{

    private List<AddAreaInfoVo> updateAreaInfoVoList;

    @Override
    public void writeBinaryData(){

        SdkUploadMsgProto.SdkUpdateAreaMsg.Builder build = SdkUploadMsgProto.SdkUpdateAreaMsg.newBuilder();
        for(AddAreaInfoVo addAreaInfoVo : updateAreaInfoVoList){
            SdkUploadMsgProto.SdkUpdateAreaInfo.Builder second = SdkUploadMsgProto.SdkUpdateAreaInfo.newBuilder();
            second.setAreaId(addAreaInfoVo.getAreaId());
            second.setAreaName(addAreaInfoVo.getAreaName());
            build.addAreaList(second.build());
        }
        byte[] bytes = build.build().toByteArray();
        for(byte b : bytes){
            writeByte(b);
        }
    }

    public List<AddAreaInfoVo> getUpdateAreaInfoVoList() {
        return updateAreaInfoVoList;
    }

    public void setUpdateAreaInfoVoList(List<AddAreaInfoVo> updateAreaInfoVoList) {
        this.updateAreaInfoVoList = updateAreaInfoVoList;
    }
}
