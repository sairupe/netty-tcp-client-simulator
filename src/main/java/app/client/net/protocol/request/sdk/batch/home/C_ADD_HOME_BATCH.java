package app.client.net.protocol.request.sdk.batch.home;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import com.gowild.sdk.protocol.Device2TcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdk.vo.db.AddHomeInfoVo;
import com.gowild.sdk.metadata.pb.Sdk2TcpMsgProto;

import java.util.List;


@Protocol(moduleId = SdkMsgType.XB_CLIENT_TYPE, sequenceId = Device2TcpProtocol.SDK_ADD_HOME_BATCH_C, type = ProtocolType.REQUSET)
public class C_ADD_HOME_BATCH extends RequestProtocol{

    private List<AddHomeInfoVo> addHomeInfoVoList;

    @Override
    public void writeBinaryData(){

        Sdk2TcpMsgProto.SdkAddHomeBatchMsg.Builder build = Sdk2TcpMsgProto.SdkAddHomeBatchMsg.newBuilder();
        for(AddHomeInfoVo addHomeInfoVo : addHomeInfoVoList){
            Sdk2TcpMsgProto.SdkAddHome.Builder second = Sdk2TcpMsgProto.SdkAddHome.newBuilder();
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
