package app.client.net.protocol.request.sdk.single.scene;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import com.gowild.sdk.metadata.pb.Sdk2TcpMsgProto;
import com.gowild.sdk.protocol.Device2TcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdk.vo.db.AddSceneInfoVo;

import java.util.List;


@Protocol(moduleId = SdkMsgType.XB_CLIENT_TYPE, sequenceId = Device2TcpProtocol.SDK_UPDATE_SCENE_C, type = ProtocolType.REQUSET)
public class C_UPDATE_SCENE extends RequestProtocol{

    private List<AddSceneInfoVo> updateSceneInfoVoList;

    @Override
    public void writeBinaryData(){

        Sdk2TcpMsgProto.SdkUpdateSceneBatchMsg.Builder build = Sdk2TcpMsgProto.SdkUpdateSceneBatchMsg.newBuilder();
        for(AddSceneInfoVo addSceneInfoVo : updateSceneInfoVoList){
            Sdk2TcpMsgProto.SdkUpdateScene.Builder second = Sdk2TcpMsgProto.SdkUpdateScene.newBuilder();
            second.setSceneId(addSceneInfoVo.getSceneTid());
            second.setSceneName(addSceneInfoVo.getSceneName());
            build.addSceneList(second.build());
        }
        byte[] bytes = build.build().toByteArray();
        for(byte b : bytes){
            writeByte(b);
        }
    }

    public List<AddSceneInfoVo> getUpdateSceneInfoVoList() {
        return updateSceneInfoVoList;
    }

    public void setUpdateSceneInfoVoList(List<AddSceneInfoVo> updateSceneInfoVoList) {
        this.updateSceneInfoVoList = updateSceneInfoVoList;
    }
}
