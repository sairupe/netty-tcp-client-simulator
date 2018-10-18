package app.client.service.sdk.scene;

import app.client.net.annotation.Receiver;
import app.client.net.protocol.response.sdk.batch.scene.S_ADD_SCENE_BATCH;
import app.client.net.protocol.response.sdk.batch.scene.S_DELETE_SCENE;
import app.client.net.protocol.response.sdk.batch.scene.S_SYNC_SCENE;
import app.client.net.protocol.response.sdk.batch.scene.S_UPDATE_SCENE_BATCH;
import app.client.net.protocol.response.sdk.single.scene.S_ADD_SCENE;
import app.client.net.protocol.response.sdk.single.scene.S_UPDATE_SCENE;
import app.client.service.AbstractServiceImpl;
import app.client.service.sdk.floor.FloorServiceImpl;
import com.gowild.sdk.metadata.pb.SdkBothMsgProto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zh on 2018/2/27.
 */
@Receiver
public class SceneServiceImpl extends AbstractServiceImpl implements ISceneService {

    Logger logger = LoggerFactory.getLogger(FloorServiceImpl.class);

    @Override
    public void syncSceneResult(S_SYNC_SCENE response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        logger.info("====== >>> SDK【同步】场景返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void deleteSceneResult(S_DELETE_SCENE response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        logger.info("====== >>> SDK【删除】场景返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void addSceneResult(S_ADD_SCENE response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        logger.info("====== >>> SDK【添加】场景返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void updateSceneResult(S_UPDATE_SCENE response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        logger.info("====== >>> SDK【更新】场景返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void addSceneBatchResult(S_ADD_SCENE_BATCH response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        logger.info("====== >>> SDK【批量添加】场景返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void updateSceneBatchResult(S_UPDATE_SCENE_BATCH response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        logger.info("====== >>> SDK【批量更新】场景返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }
}
