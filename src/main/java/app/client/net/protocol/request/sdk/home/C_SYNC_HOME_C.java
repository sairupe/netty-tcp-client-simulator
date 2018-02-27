package app.client.net.protocol.request.sdk.home;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import app.client.net.protocol.request.sdk.vo.AddDeviceInfoVo;
import com.gowild.core.util.StringUtil;
import com.gowild.protocol.Device2SdkTcpProtocol;
import com.gowild.protocol.SdkMsgType;
import com.gowild.sdktcp.metadata.pb.SdkUploadMsgProto;
import com.gowild.vo.db.AddHomeInfoVo;

import java.util.List;


@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Device2SdkTcpProtocol.SDK_SYNC_HOME_C, type = ProtocolType.REQUSET)
public class C_SYNC_HOME_C extends RequestProtocol{

    private List<AddHomeInfoVo> syncHomeInfoVoList;

    @Override
    public void writeBinaryData(){

        SdkUploadMsgProto.SdkAddHomeMsg.Builder build = SdkUploadMsgProto.SdkAddHomeMsg.newBuilder();
        for(AddHomeInfoVo addHomeInfoVo : syncHomeInfoVoList){
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

    public List<AddHomeInfoVo> getSyncHomeInfoVoList() {
        return syncHomeInfoVoList;
    }

    public void setSyncHomeInfoVoList(List<AddHomeInfoVo> syncHomeInfoVoList) {
        this.syncHomeInfoVoList = syncHomeInfoVoList;
    }
}
