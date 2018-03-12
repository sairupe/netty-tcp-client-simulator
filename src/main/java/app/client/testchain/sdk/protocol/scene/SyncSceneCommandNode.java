package app.client.testchain.sdk.protocol.scene;

import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.sdk.scene.C_SYNC_SCENE_C;
import app.client.testchain.ProtocolListenNode;
import app.client.testchain.sdk.SdkTestConst;
import com.gowild.sdk.vo.db.AddSceneInfoVo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zh on 2018/2/27.
 */
public class SyncSceneCommandNode extends ProtocolListenNode {
    @Override
    public void doExecute() {

        List<AddSceneInfoVo> addSceneInfoVoList = new ArrayList<>();

        String sceneTid1 = SdkTestConst.FIRST_SCENE_TID;
        String sceneName1 = SdkTestConst.FIRST_SCENE_NAME;
        AddSceneInfoVo addSceneInfoVo1 = new AddSceneInfoVo();
        addSceneInfoVo1.setSceneTid(sceneTid1);
        addSceneInfoVo1.setSceneName(sceneName1);

        String sceneTid2 = SdkTestConst.SECOND_SCENE_TID;
        String sceneName2 = SdkTestConst.SECOND_SCENE_NAME;
        AddSceneInfoVo addSceneInfoVo2 = new AddSceneInfoVo();
        addSceneInfoVo2.setSceneTid(sceneTid2);
        addSceneInfoVo2.setSceneName(sceneName2);

        addSceneInfoVoList.add(addSceneInfoVo1);
        addSceneInfoVoList.add(addSceneInfoVo2);

        C_SYNC_SCENE_C protocol = ProtocolFactory.createRequestProtocol(C_SYNC_SCENE_C.class,
                userSession.getCtx());
        protocol.setSyncSceneInfoVoList(addSceneInfoVoList);
        userSession.sendMsg(protocol);
    }

    @Override
    public boolean canExecuteImmediately(){
        return true;
    }
}
