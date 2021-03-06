package app.client.net.protocol.request.sdk.single.scene;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import com.gowild.sdk.metadata.pb.Sdk2TcpMsgProto;
import com.gowild.sdk.protocol.Device2TcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdk.vo.db.AddSceneInfoVo;

import java.util.List;


@Protocol(moduleId = SdkMsgType.XB_CLIENT_TYPE, sequenceId = Device2TcpProtocol.SDK_ADD_SCENE_C, type = ProtocolType.REQUSET)
public class C_ADD_SCENE extends RequestProtocol{

    private List<AddSceneInfoVo> addSceneInfoVoList;

    @Override
    public void writeBinaryData(){
        Sdk2TcpMsgProto.SdkAddSceneBatchMsg.Builder build = Sdk2TcpMsgProto.SdkAddSceneBatchMsg.newBuilder();
        for(AddSceneInfoVo addSceneInfoVo : addSceneInfoVoList){
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

    public List<AddSceneInfoVo> getAddSceneInfoVoList() {
        return addSceneInfoVoList;
    }

    public void setAddSceneInfoVoList(List<AddSceneInfoVo> addSceneInfoVoList) {
        this.addSceneInfoVoList = addSceneInfoVoList;
    }
}
