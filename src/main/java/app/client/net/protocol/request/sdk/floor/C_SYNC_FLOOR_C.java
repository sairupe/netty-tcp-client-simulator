package app.client.net.protocol.request.sdk.floor;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import app.client.net.protocol.request.sdk.vo.AddFloorInfoVo;
import com.gowild.core.util.StringUtil;
import com.gowild.protocol.Device2SdkTcpProtocol;
import com.gowild.protocol.SdkMsgType;
import com.gowild.sdktcp.metadata.pb.SdkUploadMsgProto;
import com.gowild.vo.db.AddHomeInfoVo;

import java.util.List;


@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Device2SdkTcpProtocol.SDK_SYNC_FLOOR_C, type = ProtocolType.REQUSET)
public class C_SYNC_FLOOR_C extends RequestProtocol{

    private List<AddFloorInfoVo> syncFloorInfoVoList;

    @Override
    public void writeBinaryData(){

        SdkUploadMsgProto.SdkAddFloorMsg.Builder build = SdkUploadMsgProto.SdkAddFloorMsg.newBuilder();
        for(AddFloorInfoVo addFloorInfoVo : syncFloorInfoVoList){
            SdkUploadMsgProto.SdkAddFloorInfo.Builder second = SdkUploadMsgProto.SdkAddFloorInfo.newBuilder();
            second.setFloorId(addFloorInfoVo.getFloorId());
            second.setFloorName(addFloorInfoVo.getFloorName());
            String bindHomeId = addFloorInfoVo.getBindHomeId();
            if (!StringUtil.isNullOrEmpty(bindHomeId)) {
                second.setHomeId(addFloorInfoVo.getBindHomeId());
            }
            build.addFloorList(second.build());
        }
        byte[] bytes = build.build().toByteArray();
        for(byte b : bytes){
            writeByte(b);
        }
    }

    public List<AddFloorInfoVo> getSyncFloorInfoVoList() {
        return syncFloorInfoVoList;
    }

    public void setSyncFloorInfoVoList(List<AddFloorInfoVo> syncFloorInfoVoList) {
        this.syncFloorInfoVoList = syncFloorInfoVoList;
    }
}
