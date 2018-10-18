package app.client.testchain.sdk.protocol.device;

import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.sdk.batch.device.C_UPDATE_DEVICE_BIND_SCENE_BATCH;
import app.client.vo.UpdateDeviceBindScene;
import app.client.testchain.ProtocolListenNode;
import app.client.testchain.sdk.SdkTestConst;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zh on 2017/11/21.
 */
public class SdkUpdateDeviceBindSceneBatchCommandNode extends ProtocolListenNode {
    @Override
    public void doExecute() {

        String device1 = SdkTestConst.FIRST_DEVICE_UID;
        String sceneId1 = SdkTestConst.FIRST_SCENE_TID;

        UpdateDeviceBindScene updateDeviceBindScene1 = new UpdateDeviceBindScene();
        updateDeviceBindScene1.setDeviceId(device1);
        updateDeviceBindScene1.setSceneId(sceneId1);

        String device2 = SdkTestConst.SECOND_DEVICE_UID;
        String sceneId2 = SdkTestConst.SECOND_SCENE_TID;

        UpdateDeviceBindScene updateDeviceBindScene2 = new UpdateDeviceBindScene();
        updateDeviceBindScene2.setDeviceId(device2);
        updateDeviceBindScene2.setSceneId(sceneId2);

        List<UpdateDeviceBindScene> updateList = new ArrayList<>();
        updateList.add(updateDeviceBindScene1);
        updateList.add(updateDeviceBindScene2);

        C_UPDATE_DEVICE_BIND_SCENE_BATCH updateDeviceBindScene = ProtocolFactory.createRequestProtocol(C_UPDATE_DEVICE_BIND_SCENE_BATCH.class,
                userSession.getCtx());
        updateDeviceBindScene.setUpdateDeviceBindSceneList(updateList);
        userSession.sendMsg(updateDeviceBindScene);
    }

    @Override
    public boolean canExecuteImmediately(){
        return true;
    }
}
