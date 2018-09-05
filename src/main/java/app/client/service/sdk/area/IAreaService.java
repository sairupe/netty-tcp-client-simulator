package app.client.service.sdk.area;

import app.client.net.protocol.response.sdk.batch.area.S_ADD_AREA_BATCH;
import app.client.net.protocol.response.sdk.batch.area.S_DELETE_AREA;
import app.client.net.protocol.response.sdk.batch.area.S_SYNC_AREA;
import app.client.net.protocol.response.sdk.batch.area.S_UPDATE_AREA_BATCH;
import app.client.net.protocol.response.sdk.batch.area.S_UPDATE_AREA_BIND_FLOOR_BATCH;
import app.client.net.protocol.response.sdk.single.area.S_ADD_AREA;
import app.client.net.protocol.response.sdk.single.area.S_UPDATE_AREA;
import app.client.net.protocol.response.sdk.single.area.S_UPDATE_AREA_BIND_FLOOR;

/**
 * 
 * @author syriana.zh
 *
 * 2016年4月21日 下午3:21:18
 */
public interface IAreaService{

    public void addAreaResult(S_ADD_AREA response);

    public void updateAreaResult(S_UPDATE_AREA response);

    public void updateAreaBindFloorResult(S_UPDATE_AREA_BIND_FLOOR response);


    public void addAreaBatchResult(S_ADD_AREA_BATCH response);

    public void updateAreaBatchResult(S_UPDATE_AREA_BATCH response);

    public void updateAreaBindFloorBatchResult(S_UPDATE_AREA_BIND_FLOOR_BATCH response);


    public void syncAreaResult(S_SYNC_AREA response);

    public void deleteAreaResult(S_DELETE_AREA response);
}
