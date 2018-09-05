package app.client.service.sdk.home;

import app.client.net.protocol.response.sdk.batch.home.S_ADD_HOME_BATCH;
import app.client.net.protocol.response.sdk.batch.home.S_DELETE_HOME;
import app.client.net.protocol.response.sdk.batch.home.S_SYNC_HOME;
import app.client.net.protocol.response.sdk.batch.home.S_UPDATE_HOME_BATCH;
import app.client.net.protocol.response.sdk.single.home.S_ADD_HOME;
import app.client.net.protocol.response.sdk.single.home.S_UPDATE_HOME;
import app.client.service.IService;

/**
 * 
 * @author syriana.zh
 *
 * 2016年4月21日 下午3:21:18
 */
public interface IHomeService extends IService{

    public void syncHomeResult(S_SYNC_HOME response);

    public void deleteHomeResult(S_DELETE_HOME response);

    public void addHomeResult(S_ADD_HOME response);

    public void updateHomeResult(S_UPDATE_HOME response);

    public void addHomeBatchResult(S_ADD_HOME_BATCH response);

    public void updateHomeBatchResult(S_UPDATE_HOME_BATCH response);
}
