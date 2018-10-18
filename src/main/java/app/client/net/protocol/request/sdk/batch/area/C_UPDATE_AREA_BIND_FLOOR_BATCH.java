package app.client.net.protocol.request.sdk.batch.area;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import app.client.vo.UpdateAreaBindFloor;
import com.gowild.core.util.StringUtil;
import com.gowild.sdk.protocol.Device2TcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdk.metadata.pb.Sdk2TcpMsgProto;

import java.util.List;


@Protocol(moduleId = SdkMsgType.XB_CLIENT_TYPE, sequenceId = Device2TcpProtocol.SDK_UPDATE_AREA_BIND_FLOOR_BATCH_C, type = ProtocolType.REQUSET)
public class C_UPDATE_AREA_BIND_FLOOR_BATCH extends RequestProtocol{

    private List<UpdateAreaBindFloor> updateAreaBindFloorList;

    @Override
    public void writeBinaryData(){
        Sdk2TcpMsgProto.SdkUpdateAreaBindFloorBatchMsg.Builder build = Sdk2TcpMsgProto.SdkUpdateAreaBindFloorBatchMsg.newBuilder();
        for(UpdateAreaBindFloor updateAreaBindFloor : updateAreaBindFloorList){
            Sdk2TcpMsgProto.SdkUpdateAreaBindFloor.Builder second = Sdk2TcpMsgProto.SdkUpdateAreaBindFloor.newBuilder();
            second.setAreaId(updateAreaBindFloor.getAreaId());
            String floorId = updateAreaBindFloor.getFloorId();
            if(!StringUtil.isNullOrEmpty(floorId)){
                second.setFloorId(floorId);
            }
            build.addAreaBindList(second.build());
        }
        byte[] bytes = build.build().toByteArray();
        for(byte b : bytes){
            writeByte(b);
        }
    }

    public List<UpdateAreaBindFloor> getUpdateAreaBindFloorList() {
        return updateAreaBindFloorList;
    }

    public void setUpdateAreaBindFloorList(List<UpdateAreaBindFloor> updateAreaBindFloorList) {
        this.updateAreaBindFloorList = updateAreaBindFloorList;
    }
}
