package app.client.net.protocol.request.sdk.floor;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import app.client.net.protocol.request.sdk.vo.AddFloorInfoVo;
import com.gowild.core.util.StringUtil;
import com.gowild.sdk.protocol.Device2TcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdk.metadata.pb.Sdk2TcpMsgProto;

import java.util.List;


@Protocol(moduleId = SdkMsgType.XB_CLIENT_TYPE, sequenceId = Device2TcpProtocol.SDK_SYNC_FLOOR_C, type = ProtocolType.REQUSET)
public class C_SYNC_FLOOR_C extends RequestProtocol{

    private List<AddFloorInfoVo> syncFloorInfoVoList;

    @Override
    public void writeBinaryData(){

        Sdk2TcpMsgProto.SdkAddFloorBatchMsg.Builder build = Sdk2TcpMsgProto.SdkAddFloorBatchMsg.newBuilder();
        for(AddFloorInfoVo addFloorInfoVo : syncFloorInfoVoList){
            Sdk2TcpMsgProto.SdkAddFloor.Builder second = Sdk2TcpMsgProto.SdkAddFloor.newBuilder();
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
