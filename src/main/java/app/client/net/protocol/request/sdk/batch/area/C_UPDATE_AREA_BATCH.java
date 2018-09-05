package app.client.net.protocol.request.sdk.batch.area;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import app.client.vo.AddAreaInfoVo;
import com.gowild.sdk.protocol.Device2TcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdk.metadata.pb.Sdk2TcpMsgProto;

import java.util.List;


@Protocol(moduleId = SdkMsgType.XB_CLIENT_TYPE, sequenceId = Device2TcpProtocol.SDK_UPDATE_AREA_BATCH_C, type = ProtocolType.REQUSET)
public class C_UPDATE_AREA_BATCH extends RequestProtocol{

    private List<AddAreaInfoVo> updateAreaInfoVoList;

    @Override
    public void writeBinaryData(){

        Sdk2TcpMsgProto.SdkUpdateAreaBatchMsg.Builder build = Sdk2TcpMsgProto.SdkUpdateAreaBatchMsg.newBuilder();
        for(AddAreaInfoVo addAreaInfoVo : updateAreaInfoVoList){
            Sdk2TcpMsgProto.SdkUpdateArea.Builder second = Sdk2TcpMsgProto.SdkUpdateArea.newBuilder();
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
