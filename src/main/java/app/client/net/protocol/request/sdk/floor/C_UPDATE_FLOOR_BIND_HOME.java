package app.client.net.protocol.request.sdk.floor;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import app.client.net.protocol.request.sdk.vo.UpdateFloorBindHome;
import com.gowild.core.util.StringUtil;
import com.gowild.sdk.protocol.Device2SdkTcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdktcp.metadata.pb.SdkUploadMsgProto;

import java.util.List;


@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Device2SdkTcpProtocol.SDK_UPDATE_FLOOR_BIND_HOME_C, type = ProtocolType.REQUSET)
public class C_UPDATE_FLOOR_BIND_HOME extends RequestProtocol{

    private List<UpdateFloorBindHome> updateFloorBindHomeList;

    @Override
    public void writeBinaryData(){
        SdkUploadMsgProto.SdkUpdateFloorBindHomeMsg.Builder build = SdkUploadMsgProto.SdkUpdateFloorBindHomeMsg.newBuilder();
        for(UpdateFloorBindHome updateFloorBindHome : updateFloorBindHomeList){
            SdkUploadMsgProto.SdkUpdateFloorBindHomeInfo.Builder second = SdkUploadMsgProto.SdkUpdateFloorBindHomeInfo.newBuilder();
            second.setFloorId(updateFloorBindHome.getFloorId());
            String homeId = updateFloorBindHome.getHomeId();
            if(!StringUtil.isNullOrEmpty(homeId)){
                second.setHomeId(homeId);
            }
            build.addFloorBindList(second.build());
        }
        byte[] bytes = build.build().toByteArray();
        for(byte b : bytes){
            writeByte(b);
        }
    }

    public List<UpdateFloorBindHome> getUpdateFloorBindHomeList() {
        return updateFloorBindHomeList;
    }

    public void setUpdateFloorBindHomeList(List<UpdateFloorBindHome> updateFloorBindHomeList) {
        this.updateFloorBindHomeList = updateFloorBindHomeList;
    }
}
