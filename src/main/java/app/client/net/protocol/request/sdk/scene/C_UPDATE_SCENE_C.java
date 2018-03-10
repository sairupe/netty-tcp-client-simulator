package app.client.net.protocol.request.sdk.scene;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import app.client.net.protocol.request.sdk.vo.AddAreaInfoVo;
import com.gowild.protocol.Device2SdkTcpProtocol;
import com.gowild.protocol.SdkMsgType;
import com.gowild.sdktcp.metadata.pb.SdkUploadMsgProto;
import com.gowild.vo.db.AddSceneInfoVo;

import java.util.List;


@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Device2SdkTcpProtocol.SDK_UPDATE_SCENE_INFO_C, type = ProtocolType.REQUSET)
public class C_UPDATE_SCENE_C extends RequestProtocol{

    private List<AddSceneInfoVo> updateSceneInfoVoList;

    @Override
    public void writeBinaryData(){

        SdkUploadMsgProto.SdkUpdateSceneMsg.Builder build = SdkUploadMsgProto.SdkUpdateSceneMsg.newBuilder();
        for(AddSceneInfoVo addSceneInfoVo : updateSceneInfoVoList){
            SdkUploadMsgProto.SdkUpdateSceneInfo.Builder second = SdkUploadMsgProto.SdkUpdateSceneInfo.newBuilder();
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
