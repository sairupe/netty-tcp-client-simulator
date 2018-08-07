package app.client.testchain.sdk.protocol.scene;

import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.sdk.batch.scene.C_DELETE_SCENE;
import app.client.testchain.ProtocolListenNode;
import app.client.testchain.sdk.SdkTestConst;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zh on 2018/2/27.
 */
public class DeleteSceneCommandNode extends ProtocolListenNode {
    @Override
    public void doExecute() {
        String firstSceneId = SdkTestConst.FIRST_SCENE_TID;
        String secondSceneId = SdkTestConst.SECOND_SCENE_TID;
        List<String> deleteSceneIdList = new ArrayList<>();
        deleteSceneIdList.add(firstSceneId);
        deleteSceneIdList.add(secondSceneId);

        C_DELETE_SCENE protocol = ProtocolFactory.createRequestProtocol(C_DELETE_SCENE.class,
                userSession.getCtx());
        protocol.setDeleteAreaIdList(deleteSceneIdList);
        userSession.sendMsg(protocol);
    }

    @Override
    public boolean canExecuteImmediately(){
        return true;
    }
}
