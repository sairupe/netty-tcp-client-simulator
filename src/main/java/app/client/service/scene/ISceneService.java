package app.client.service.scene;

import app.client.net.protocol.response.sdk.home.S_ADD_HOME_RESULT;
import app.client.net.protocol.response.sdk.home.S_DELETE_HOME_RESULT;
import app.client.net.protocol.response.sdk.home.S_SYNC_HOME_RESULT;
import app.client.net.protocol.response.sdk.home.S_UPDATE_HOME_RESULT;
import app.client.net.protocol.response.sdk.scene.S_ADD_SCENE_RESULT;
import app.client.net.protocol.response.sdk.scene.S_DELETE_SCENE_RESULT;
import app.client.net.protocol.response.sdk.scene.S_SYNC_SCENE_RESULT;
import app.client.net.protocol.response.sdk.scene.S_UPDATE_SCENE_RESULT;
import app.client.service.IService;

/**
 * 
 * @author syriana.zh
 *
 * 2016年4月21日 下午3:21:18
 */
public interface ISceneService extends IService{
	
    public void addSceneResult(S_ADD_SCENE_RESULT response);

    public void deleteSceneResult(S_DELETE_SCENE_RESULT response);

    public void updateSceneResult(S_UPDATE_SCENE_RESULT response);

    public void syncSceneResult(S_SYNC_SCENE_RESULT response);
}
