package app.client.net.protocol.request.sdk.scene;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import com.gowild.sdk.protocol.Device2SdkTcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.sdk.vo.db.AddSceneInfoVo;
import com.gowild.sdktcp.metadata.pb.SdkUploadMsgProto;

import java.util.List;


@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Device2SdkTcpProtocol.SDK_ADD_SCENE_INFO_C, type = ProtocolType.REQUSET)
public class C_ADD_SCENE_C extends RequestProtocol{

    private List<AddSceneInfoVo> addSceneInfoVoList;

    @Override
    public void writeBinaryData(){
        SdkUploadMsgProto.SdkAddSceneMsg.Builder build = SdkUploadMsgProto.SdkAddSceneMsg.newBuilder();
        for(AddSceneInfoVo addSceneInfoVo : addSceneInfoVoList){
            SdkUploadMsgProto.SdkAddSceneInfo.Builder second = SdkUploadMsgProto.SdkAddSceneInfo.newBuilder();
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
