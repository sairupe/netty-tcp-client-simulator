package app.client.net.protocol.request.sdk.area;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import app.client.net.protocol.request.sdk.vo.AddAreaInfoVo;
import com.gowild.core.util.StringUtil;
import com.gowild.sdk.protocol.Device2SdkTcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdktcp.metadata.pb.SdkUploadMsgProto;

import java.util.List;


@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Device2SdkTcpProtocol.SDK_ADD_AREA_INFO_C, type = ProtocolType.REQUSET)
public class C_ADD_AREA_C extends RequestProtocol{

    private List<AddAreaInfoVo> addAreaInfoVoList;

    @Override
    public void writeBinaryData(){
        SdkUploadMsgProto.SdkAddAreaMsg.Builder build = SdkUploadMsgProto.SdkAddAreaMsg.newBuilder();
        for(AddAreaInfoVo addAreaInfoVo : addAreaInfoVoList){
            SdkUploadMsgProto.SdkAddAreaInfo.Builder second = SdkUploadMsgProto.SdkAddAreaInfo.newBuilder();
            second.setAreaId(addAreaInfoVo.getAreaId());
            second.setAreaName(addAreaInfoVo.getAreaName());
            String bindFloorId = addAreaInfoVo.getBindFloorId();
            if (!StringUtil.isNullOrEmpty(bindFloorId)) {
                second.setFloorId(addAreaInfoVo.getBindFloorId());
            }
            build.addAreaList(second.build());
        }
        byte[] bytes = build.build().toByteArray();
        for(byte b : bytes){
            writeByte(b);
        }
    }

    public List<AddAreaInfoVo> getAddAreaInfoVoList() {
        return addAreaInfoVoList;
    }

    public void setAddAreaInfoVoList(List<AddAreaInfoVo> addAreaInfoVoList) {
        this.addAreaInfoVoList = addAreaInfoVoList;
    }
}
