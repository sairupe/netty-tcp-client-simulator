package app.client.service.sdk.area;

import app.client.common.TimeRecordKey;
import app.client.net.annotation.Receiver;
import app.client.net.protocol.response.sdk.batch.area.S_ADD_AREA_BATCH;
import app.client.net.protocol.response.sdk.batch.area.S_DELETE_AREA;
import app.client.net.protocol.response.sdk.batch.area.S_SYNC_AREA;
import app.client.net.protocol.response.sdk.batch.area.S_UPDATE_AREA_BATCH;
import app.client.net.protocol.response.sdk.batch.area.S_UPDATE_AREA_BIND_FLOOR_BATCH;
import app.client.net.protocol.response.sdk.single.area.S_ADD_AREA;
import app.client.net.protocol.response.sdk.single.area.S_UPDATE_AREA;
import app.client.net.protocol.response.sdk.single.area.S_UPDATE_AREA_BIND_FLOOR;
import app.client.service.AbstractServiceImpl;
import com.gowild.sdk.metadata.pb.SdkBothMsgProto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zh on 2018/2/27.
 */
@Receiver
public class AreaServiceImpl extends AbstractServiceImpl implements IAreaService {

    Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);

    @Override
    public void addAreaResult(S_ADD_AREA response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        logger.info("====== >>> SDK【添加】区域返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void deleteAreaResult(S_DELETE_AREA response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        logger.info("====== >>> SDK【删除】区域返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void updateAreaResult(S_UPDATE_AREA response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        logger.info("====== >>> SDK【更新】区域返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void syncAreaResult(S_SYNC_AREA response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        logger.info("====== >>> SDK【同步】区域返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void updateAreaBindFloorResult(S_UPDATE_AREA_BIND_FLOOR response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        logger.info("====== >>> SDK【更新】区域绑定楼层返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void addAreaBatchResult(S_ADD_AREA_BATCH response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        logger.info("====== >>> SDK【批量添加】区域返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
        response.getUserSession().markTimeEnd(TimeRecordKey.SDK_ADD_AREA_TIME);
    }

    @Override
    public void updateAreaBatchResult(S_UPDATE_AREA_BATCH response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        logger.info("====== >>> SDK【批量更新】区域返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());

    }

    @Override
    public void updateAreaBindFloorBatchResult(S_UPDATE_AREA_BIND_FLOOR_BATCH response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        logger.info("====== >>> SDK【批量更新】区域绑定楼层返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }
}
