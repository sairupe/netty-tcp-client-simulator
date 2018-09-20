package app.client.service.plugin;

import app.client.net.annotation.Receiver;
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
import app.client.service.AbstractServiceImpl;
import app.client.service.sdk.area.AreaServiceImpl;
import com.gowild.sdk.metadata.pb.SdkBothMsgProto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zh on 2018/2/27.
 */
@Receiver
public class PluginServiceImpl extends AbstractServiceImpl implements IPluginService {

    Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);

    @Override
    public void timingDeleteAppResult(S_DEVICE_TIMING_DELETE_APP response) {
        logger.info("====== >>> APP收到插座删除定时通知");
    }

    @Override
    public void timingDeleteRobotResult(S_DEVICE_TIMING_DELETE_ROBOT response) {
        logger.info("====== >>> ROBOT收到插座删除定时通知");
    }

    @Override
    public void timingExecuteNotifyAppResult(S_DEVICE_TIMING_EXECUTED_APP response) {
        logger.info("====== >>> APP收到插座定时触发通知");
    }
}
