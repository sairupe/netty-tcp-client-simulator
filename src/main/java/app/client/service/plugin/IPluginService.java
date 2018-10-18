package app.client.service.plugin;

import app.client.net.protocol.response.plugin.S_DEVICE_TIMING_DELETE_APP;
import app.client.net.protocol.response.plugin.S_DEVICE_TIMING_DELETE_ROBOT;
import app.client.net.protocol.response.plugin.S_DEVICE_TIMING_EXECUTED_APP;
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
public interface IPluginService{

    public void timingDeleteAppResult(S_DEVICE_TIMING_DELETE_APP response);

    public void timingDeleteRobotResult(S_DEVICE_TIMING_DELETE_ROBOT response);

    public void timingExecuteNotifyAppResult(S_DEVICE_TIMING_EXECUTED_APP response);
}
