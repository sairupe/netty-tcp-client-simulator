package app.client.net.protocol.request.sdk.single.device;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import app.client.net.protocol.request.sdk.vo.UpdateDeviceBindScene;
import com.gowild.core.util.StringUtil;
import com.gowild.sdk.metadata.pb.Sdk2TcpMsgProto;
import com.gowild.sdk.protocol.Device2TcpProtocol;
import com.gowild.sdk.protocol.SdkMsgType;

import java.util.List;


@Protocol(moduleId = SdkMsgType.XB_CLIENT_TYPE, sequenceId = Device2TcpProtocol.SDK_UPDATE_DEVICE_BIND_SCENE_C, type = ProtocolType.REQUSET)
public class C_UPDATE_DEVICE_BIND_SCENE extends RequestProtocol{

    private List<UpdateDeviceBindScene> updateDeviceBindSceneList;

    @Override
    public void writeBinaryData(){
        Sdk2TcpMsgProto.SdkUpdateDeviceBindSceneBatchMsg.Builder build = Sdk2TcpMsgProto.SdkUpdateDeviceBindSceneBatchMsg.newBuilder();
        for(UpdateDeviceBindScene updateDeviceBindScene : updateDeviceBindSceneList){
            Sdk2TcpMsgProto.SdkUpdateDeviceScene.Builder second = Sdk2TcpMsgProto.SdkUpdateDeviceScene.newBuilder();
            second.setDeviceId(updateDeviceBindScene.getDeviceId());
            String sceneId = updateDeviceBindScene.getSceneId();
            if(!StringUtil.isNullOrEmpty(sceneId)){
                second.setSceneId(sceneId);
            }
            build.addDeviceSceneList(second.build());
        }
        byte[] bytes = build.build().toByteArray();
        for(byte b : bytes){
            writeByte(b);
        }
    }

    public List<UpdateDeviceBindScene> getUpdateDeviceBindSceneList() {
        return updateDeviceBindSceneList;
    }

    public void setUpdateDeviceBindSceneList(List<UpdateDeviceBindScene> updateDeviceBindSceneList) {
        this.updateDeviceBindSceneList = updateDeviceBindSceneList;
    }
}
