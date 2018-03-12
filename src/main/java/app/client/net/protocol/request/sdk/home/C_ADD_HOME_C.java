package app.client.net.protocol.request.sdk.home;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import com.gowild.sdk.protocol.Device2SdkTcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdk.vo.db.AddHomeInfoVo;
import com.gowild.sdktcp.metadata.pb.SdkUploadMsgProto;

import java.util.List;


@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Device2SdkTcpProtocol.SDK_ADD_HOME_INFO_C, type = ProtocolType.REQUSET)
public class C_ADD_HOME_C extends RequestProtocol{

    private List<AddHomeInfoVo> addHomeInfoVoList;

    @Override
    public void writeBinaryData(){

        SdkUploadMsgProto.SdkAddHomeMsg.Builder build = SdkUploadMsgProto.SdkAddHomeMsg.newBuilder();
        for(AddHomeInfoVo addHomeInfoVo : addHomeInfoVoList){
            SdkUploadMsgProto.SdkAddHomeInfo.Builder second = SdkUploadMsgProto.SdkAddHomeInfo.newBuilder();
            second.setHomeId(addHomeInfoVo.getHomeTid());
            second.setHomeName(addHomeInfoVo.getHomeName());
            build.addHomeList(second.build());
        }
        byte[] bytes = build.build().toByteArray();
        for(byte b : bytes){
            writeByte(b);
        }
    }

    public List<AddHomeInfoVo> getAddHomeInfoVoList() {
        return addHomeInfoVoList;
    }

    public void setAddHomeInfoVoList(List<AddHomeInfoVo> addHomeInfoVoList) {
        this.addHomeInfoVoList = addHomeInfoVoList;
    }
}
