package app.client.net.protocol.request.sdk.area;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import app.client.net.protocol.request.sdk.vo.UpdateAreaBindFloor;
import com.gowild.core.util.StringUtil;
import com.gowild.sdk.protocol.Device2SdkTcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdktcp.metadata.pb.SdkUploadMsgProto;

import java.util.List;


@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Device2SdkTcpProtocol.SDK_UPDATE_AREA_BIND_FLOOR_C, type = ProtocolType.REQUSET)
public class C_UPDATE_AREA_BIND_FLOOR extends RequestProtocol{

    private List<UpdateAreaBindFloor> updateAreaBindFloorList;

    @Override
    public void writeBinaryData(){
        SdkUploadMsgProto.SdkUpdateAreaBindFloorMsg.Builder build = SdkUploadMsgProto.SdkUpdateAreaBindFloorMsg.newBuilder();
        for(UpdateAreaBindFloor updateAreaBindFloor : updateAreaBindFloorList){
            SdkUploadMsgProto.SdkUpdateAreaBindFloorInfo.Builder second = SdkUploadMsgProto.SdkUpdateAreaBindFloorInfo.newBuilder();
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
