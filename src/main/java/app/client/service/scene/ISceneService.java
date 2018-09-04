package app.client.service.scene;

import app.client.net.protocol.response.sdk.batch.scene.S_ADD_SCENE_BATCH;
import app.client.net.protocol.response.sdk.batch.scene.S_DELETE_SCENE;
import app.client.net.protocol.response.sdk.batch.scene.S_SYNC_SCENE;
import app.client.net.protocol.response.sdk.batch.scene.S_UPDATE_SCENE_BATCH;
import app.client.net.protocol.response.sdk.single.scene.S_ADD_SCENE;
import app.client.net.protocol.response.sdk.single.scene.S_UPDATE_SCENE;
import app.client.service.IService;

/**
 * 
 * @author syriana.zh
 *
 * 2016年4月21日 下午3:21:18
 */
public interface ISceneService{

    public void syncSceneResult(S_SYNC_SCENE response);

    public void deleteSceneResult(S_DELETE_SCENE response);
	

    public void addSceneResult(S_ADD_SCENE response);

    public void updateSceneResult(S_UPDATE_SCENE response);


    public void addSceneBatchResult(S_ADD_SCENE_BATCH response);

    public void updateSceneBatchResult(S_UPDATE_SCENE_BATCH response);

}
