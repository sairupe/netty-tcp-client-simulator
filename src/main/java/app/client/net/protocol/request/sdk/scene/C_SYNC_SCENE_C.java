package app.client.net.protocol.request.sdk.scene;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import app.client.net.protocol.request.sdk.vo.AddAreaInfoVo;
import com.gowild.core.util.StringUtil;
import com.gowild.protocol.Device2SdkTcpProtocol;
import com.gowild.protocol.SdkMsgType;
import com.gowild.sdktcp.metadata.pb.SdkUploadMsgProto;
import com.gowild.vo.db.AddSceneInfoVo;

import java.util.List;


@Protocol(moduleId = SdkMsgType.SDK_DEVICE_CLIENT_TYPE, sequenceId = Device2SdkTcpProtocol.SDK_SYNC_SCENE_C, type = ProtocolType.REQUSET)
public class C_SYNC_SCENE_C extends RequestProtocol{

    private List<AddSceneInfoVo> syncSceneInfoVoList;

    @Override
    public void writeBinaryData(){
        SdkUploadMsgProto.SdkAddSceneMsg.Builder build = SdkUploadMsgProto.SdkAddSceneMsg.newBuilder();
        for(AddSceneInfoVo addSceneInfoVo : syncSceneInfoVoList){
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

    public List<AddSceneInfoVo> getSyncSceneInfoVoList() {
        return syncSceneInfoVoList;
    }

    public void setSyncSceneInfoVoList(List<AddSceneInfoVo> syncSceneInfoVoList) {
        this.syncSceneInfoVoList = syncSceneInfoVoList;
    }
}
