package app.client.service.scene;

import app.client.net.annotation.Receiver;
import app.client.net.protocol.response.sdk.batch.scene.S_ADD_SCENE_BATCH;
import app.client.net.protocol.response.sdk.batch.scene.S_DELETE_SCENE;
import app.client.net.protocol.response.sdk.batch.scene.S_SYNC_SCENE;
import app.client.net.protocol.response.sdk.batch.scene.S_UPDATE_SCENE_BATCH;
import app.client.net.protocol.response.sdk.single.scene.S_ADD_SCENE;
import app.client.net.protocol.response.sdk.single.scene.S_UPDATE_SCENE;
import app.client.service.AbstractServiceImpl;
import com.gowild.sdk.metadata.pb.SdkBothMsgProto;

/**
 * Created by zh on 2018/2/27.
 */
@Receiver
public class SceneServiceImpl extends AbstractServiceImpl implements ISceneService {

    @Override
    public void syncSceneResult(S_SYNC_SCENE response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【同步】场景返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void deleteSceneResult(S_DELETE_SCENE response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【删除】场景返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void addSceneResult(S_ADD_SCENE response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【添加】场景返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void updateSceneResult(S_UPDATE_SCENE response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【更新】场景返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void addSceneBatchResult(S_ADD_SCENE_BATCH response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【批量添加】场景返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void updateSceneBatchResult(S_UPDATE_SCENE_BATCH response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【批量更新】场景返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }
}
