package app.client.service.floor;

import app.client.net.protocol.response.sdk.floor.S_ADD_FLOOR_RESULT;
import app.client.net.protocol.response.sdk.floor.S_DELETE_FLOOR_RESULT;
import app.client.net.protocol.response.sdk.floor.S_SYNC_FLOOR_RESULT;
import app.client.net.protocol.response.sdk.floor.S_UPDATE_FLOOR_RESULT;
import app.client.net.protocol.response.sdk.home.S_ADD_HOME_RESULT;
import app.client.net.protocol.response.sdk.home.S_DELETE_HOME_RESULT;
import app.client.net.protocol.response.sdk.home.S_SYNC_HOME_RESULT;
import app.client.net.protocol.response.sdk.home.S_UPDATE_HOME_RESULT;
import app.client.service.IService;

/**
 * 
 * @author syriana.zh
 *
 * 2016年4月21日 下午3:21:18
 */
public interface IFloorService extends IService{
	
    public void addFloorResult(S_ADD_FLOOR_RESULT response);

    public void deleteFloorResult(S_DELETE_FLOOR_RESULT response);

    public void updateFloorResult(S_UPDATE_FLOOR_RESULT response);

    public void syncFloorResult(S_SYNC_FLOOR_RESULT response);
}
