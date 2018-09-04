package app.client.service.floor;

import app.client.net.protocol.response.sdk.batch.floor.S_ADD_FLOOR_BATCH;
import app.client.net.protocol.response.sdk.batch.floor.S_DELETE_FLOOR;
import app.client.net.protocol.response.sdk.batch.floor.S_SYNC_FLOOR;
import app.client.net.protocol.response.sdk.batch.floor.S_UPDATE_FLOOR_BATCH;
import app.client.net.protocol.response.sdk.batch.floor.S_UPDATE_FLOOR_BIND_HOME_BATCH;
import app.client.net.protocol.response.sdk.single.floor.S_ADD_FLOOR;
import app.client.net.protocol.response.sdk.single.floor.S_UPDATE_FLOOR;
import app.client.net.protocol.response.sdk.single.floor.S_UPDATE_FLOOR_BIND_HOME;
import app.client.service.IService;

/**
 * 
 * @author syriana.zh
 *
 * 2016年4月21日 下午3:21:18
 */
public interface IFloorService{

    public void syncFloorResult(S_SYNC_FLOOR response);

    public void deleteFloorResult(S_DELETE_FLOOR response);


    public void addFloorResult(S_ADD_FLOOR response);

    public void updateFloorResult(S_UPDATE_FLOOR response);

    public void updateFloorBindHomeResult(S_UPDATE_FLOOR_BIND_HOME response);


    public void addFloorBatchResult(S_ADD_FLOOR_BATCH response);

    public void updateFloorBatchResult(S_UPDATE_FLOOR_BATCH response);

    public void updateFloorBindHomeBatchResult(S_UPDATE_FLOOR_BIND_HOME_BATCH response);
}
