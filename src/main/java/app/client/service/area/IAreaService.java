package app.client.service.area;

import app.client.net.protocol.response.sdk.area.S_ADD_AREA_RESULT;
import app.client.net.protocol.response.sdk.area.S_DELETE_AREA_RESULT;
import app.client.net.protocol.response.sdk.area.S_SYNC_AREA_RESULT;
import app.client.net.protocol.response.sdk.area.S_UPDATE_AREA_BIND_FLOOR;
import app.client.net.protocol.response.sdk.area.S_UPDATE_AREA_RESULT;
import app.client.net.protocol.response.sdk.floor.S_ADD_FLOOR_RESULT;
import app.client.net.protocol.response.sdk.floor.S_DELETE_FLOOR_RESULT;
import app.client.net.protocol.response.sdk.floor.S_SYNC_FLOOR_RESULT;
import app.client.net.protocol.response.sdk.floor.S_UPDATE_FLOOR_RESULT;
import app.client.service.IService;

/**
 * 
 * @author syriana.zh
 *
 * 2016年4月21日 下午3:21:18
 */
public interface IAreaService extends IService{

    public void addAreaResult(S_ADD_AREA_RESULT response);

    public void deleteAreaResult(S_DELETE_AREA_RESULT response);

    public void updateAreaResult(S_UPDATE_AREA_RESULT response);

    public void syncAreaResult(S_SYNC_AREA_RESULT response);

    public void updateAreaBindFloorResult(S_UPDATE_AREA_BIND_FLOOR response);
}
