package app.client.net.protocol.request.sdk.batch.scene;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import com.gowild.sdk.protocol.Device2TcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdk.vo.db.AddSceneInfoVo;
import com.gowild.sdk.metadata.pb.Sdk2TcpMsgProto;

import java.util.List;


@Protocol(moduleId = SdkMsgType.XB_CLIENT_TYPE, sequenceId = Device2TcpProtocol.SDK_SYNC_SCENE_C, type = ProtocolType.REQUSET)
public class C_SYNC_SCENE extends RequestProtocol{

    private List<AddSceneInfoVo> syncSceneInfoVoList;

    @Override
    public void writeBinaryData(){
        Sdk2TcpMsgProto.SdkAddSceneBatchMsg.Builder build = Sdk2TcpMsgProto.SdkAddSceneBatchMsg.newBuilder();
        for(AddSceneInfoVo addSceneInfoVo : syncSceneInfoVoList){
            Sdk2TcpMsgProto.SdkAddScene.Builder second = Sdk2TcpMsgProto.SdkAddScene.newBuilder();
            second.setSceneId(addSceneInfoVo.getSceneTid());
            second.setSceneName(addSceneInfoVo.getSceneName());
            build.addSceneList(second.build());
        }
        byte[] bytes = build.build().toByteArray();
        for(byte b : bytes){
            writeByte(b);
        }
    }

    public List<AddSceneInfoVo> getSyncSceneInfoVoList() {
        return syncSceneInfoVoList;
    }

    public void setSyncSceneInfoVoList(List<AddSceneInfoVo> syncSceneInfoVoList) {
        this.syncSceneInfoVoList = syncSceneInfoVoList;
    }
}
