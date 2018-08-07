package app.client.net.protocol.request.sdk.batch.area;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import app.client.net.protocol.request.sdk.vo.AddAreaInfoVo;
import com.gowild.core.util.StringUtil;
import com.gowild.sdk.protocol.Device2TcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdk.metadata.pb.Sdk2TcpMsgProto;

import java.util.List;


@Protocol(moduleId = SdkMsgType.XB_CLIENT_TYPE, sequenceId = Device2TcpProtocol.SDK_SYNC_AREA_C, type = ProtocolType.REQUSET)
public class C_SYNC_AREA extends RequestProtocol{

    private List<AddAreaInfoVo> syncAreaInfoVoList;

    @Override
    public void writeBinaryData(){

        Sdk2TcpMsgProto.SdkAddAreaBatchMsg.Builder build = Sdk2TcpMsgProto.SdkAddAreaBatchMsg.newBuilder();
        for(AddAreaInfoVo addAreaInfoVo : syncAreaInfoVoList){
            Sdk2TcpMsgProto.SdkAddArea.Builder second = Sdk2TcpMsgProto.SdkAddArea.newBuilder();
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

    public List<AddAreaInfoVo> getSyncAreaInfoVoList() {
        return syncAreaInfoVoList;
    }

    public void setSyncAreaInfoVoList(List<AddAreaInfoVo> syncAreaInfoVoList) {
        this.syncAreaInfoVoList = syncAreaInfoVoList;
    }
}
