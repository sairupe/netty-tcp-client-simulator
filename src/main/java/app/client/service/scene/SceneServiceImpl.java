package app.client.service.scene;

import app.client.net.annotation.Receiver;
import app.client.net.protocol.response.sdk.home.S_ADD_HOME_RESULT;
import app.client.net.protocol.response.sdk.home.S_DELETE_HOME_RESULT;
import app.client.net.protocol.response.sdk.home.S_SYNC_HOME_RESULT;
import app.client.net.protocol.response.sdk.home.S_UPDATE_HOME_RESULT;
import app.client.net.protocol.response.sdk.scene.S_ADD_SCENE_RESULT;
import app.client.net.protocol.response.sdk.scene.S_DELETE_SCENE_RESULT;
import app.client.net.protocol.response.sdk.scene.S_SYNC_SCENE_RESULT;
import app.client.net.protocol.response.sdk.scene.S_UPDATE_SCENE_RESULT;
import app.client.service.AbstractServiceImpl;
import com.gowild.sdktcp.metadata.pb.SdkBothMsgProto;

/**
 * Created by zh on 2018/2/27.
 */
@Receiver
public class SceneServiceImpl extends AbstractServiceImpl implements ISceneService {

    @Override
    public void addSceneResult(S_ADD_SCENE_RESULT response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【添加】场景返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void deleteSceneResult(S_DELETE_SCENE_RESULT response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【删除】场景返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void updateSceneResult(S_UPDATE_SCENE_RESULT response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【更新】场景返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void syncSceneResult(S_SYNC_SCENE_RESULT response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【同步】场景返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }
}
