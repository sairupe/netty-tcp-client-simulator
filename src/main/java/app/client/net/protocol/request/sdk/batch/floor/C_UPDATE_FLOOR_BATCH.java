package app.client.net.protocol.request.sdk.batch.floor;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import app.client.net.protocol.request.sdk.vo.AddFloorInfoVo;
import com.gowild.sdk.protocol.Device2TcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdk.metadata.pb.Sdk2TcpMsgProto;

import java.util.List;


@Protocol(moduleId = SdkMsgType.XB_CLIENT_TYPE, sequenceId = Device2TcpProtocol.SDK_UPDATE_FLOOR_BATCH_C, type = ProtocolType.REQUSET)
public class C_UPDATE_FLOOR_BATCH extends RequestProtocol{

    private List<AddFloorInfoVo> updateHomeInfoVoList;

    @Override
    public void writeBinaryData(){

        Sdk2TcpMsgProto.SdkUpdateFloorBatchMsg.Builder build = Sdk2TcpMsgProto.SdkUpdateFloorBatchMsg.newBuilder();
        for(AddFloorInfoVo addFloorInfoVo : updateHomeInfoVoList){
            Sdk2TcpMsgProto.SdkUpdateFloor.Builder second = Sdk2TcpMsgProto.SdkUpdateFloor.newBuilder();
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
