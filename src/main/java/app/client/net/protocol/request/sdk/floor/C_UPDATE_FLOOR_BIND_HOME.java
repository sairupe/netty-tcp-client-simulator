package app.client.net.protocol.request.sdk.floor;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import app.client.net.protocol.request.sdk.vo.UpdateFloorBindHome;
import com.gowild.core.util.StringUtil;
import com.gowild.sdk.protocol.Device2TcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdk.metadata.pb.Sdk2TcpMsgProto;

import java.util.List;


@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Device2TcpProtocol.SDK_UPDATE_FLOOR_BIND_HOME_C, type = ProtocolType.REQUSET)
public class C_UPDATE_FLOOR_BIND_HOME extends RequestProtocol{

    private List<UpdateFloorBindHome> updateFloorBindHomeList;

    @Override
    public void writeBinaryData(){
        Sdk2TcpMsgProto.SdkUpdateFloorBindHomeBatchMsg.Builder build = Sdk2TcpMsgProto.SdkUpdateFloorBindHomeBatchMsg.newBuilder();
        for(UpdateFloorBindHome updateFloorBindHome : updateFloorBindHomeList){
            Sdk2TcpMsgProto.SdkUpdateFloorBindHome.Builder second = Sdk2TcpMsgProto.SdkUpdateFloorBindHome.newBuilder();
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
