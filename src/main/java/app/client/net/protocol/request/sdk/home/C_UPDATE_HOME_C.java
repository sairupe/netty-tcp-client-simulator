package app.client.net.protocol.request.sdk.home;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import com.gowild.sdk.protocol.Device2SdkTcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdk.vo.db.AddHomeInfoVo;
import com.gowild.sdktcp.metadata.pb.SdkUploadMsgProto;

import java.util.List;


@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Device2SdkTcpProtocol.SDK_UPDATE_HOME_INFO_C, type = ProtocolType.REQUSET)
public class C_UPDATE_HOME_C extends RequestProtocol{

    private List<AddHomeInfoVo> updateHomeInfoVoList;

    @Override
    public void writeBinaryData(){

        SdkUploadMsgProto.SdkUpdateHomeMsg.Builder build = SdkUploadMsgProto.SdkUpdateHomeMsg.newBuilder();
        for(AddHomeInfoVo addHomeInfoVo : updateHomeInfoVoList){
            SdkUploadMsgProto.SdkUpdateHomeInfo.Builder second = SdkUploadMsgProto.SdkUpdateHomeInfo.newBuilder();
            second.setHomeId(addHomeInfoVo.getHomeTid());
            second.setHomeName(addHomeInfoVo.getHomeName());
            build.addHomeList(second.build());
        }
        byte[] bytes = build.build().toByteArray();
        for(byte b : bytes){
            writeByte(b);
        }
    }

    public List<AddHomeInfoVo> getUpdateHomeInfoVoList() {
        return updateHomeInfoVoList;
    }

    public void setUpdateHomeInfoVoList(List<AddHomeInfoVo> updateHomeInfoVoList) {
        this.updateHomeInfoVoList = updateHomeInfoVoList;
    }
}
