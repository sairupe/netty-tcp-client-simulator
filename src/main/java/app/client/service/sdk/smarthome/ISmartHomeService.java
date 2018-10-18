package app.client.service.sdk.smarthome;

import app.client.net.protocol.response.sdk.batch.scene.S_ADD_SCENE_BATCH;
import app.client.net.protocol.response.sdk.batch.scene.S_DELETE_SCENE;
import app.client.net.protocol.response.sdk.batch.scene.S_SYNC_SCENE;
import app.client.net.protocol.response.sdk.batch.scene.S_UPDATE_SCENE_BATCH;
import app.client.net.protocol.response.sdk.single.scene.S_ADD_SCENE;
import app.client.net.protocol.response.sdk.single.scene.S_UPDATE_SCENE;
import app.client.net.protocol.response.smarthome.S_QUERY_ROBOT_COLLA_RES;

/**
 * 
 * @author syriana.zh
 *
 * 2016年4月21日 下午3:21:18
 */
public interface ISmartHomeService{

    void queryRobotColla(S_QUERY_ROBOT_COLLA_RES response);

}
