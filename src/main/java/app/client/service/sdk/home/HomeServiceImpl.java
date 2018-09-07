package app.client.service.sdk.home;

import app.client.net.annotation.Receiver;
import app.client.net.protocol.response.sdk.batch.home.S_ADD_HOME_BATCH;
import app.client.net.protocol.response.sdk.batch.home.S_DELETE_HOME;
import app.client.net.protocol.response.sdk.batch.home.S_SYNC_HOME;
import app.client.net.protocol.response.sdk.batch.home.S_UPDATE_HOME_BATCH;
import app.client.net.protocol.response.sdk.single.home.S_ADD_HOME;
import app.client.net.protocol.response.sdk.single.home.S_UPDATE_HOME;
import app.client.service.AbstractServiceImpl;
import app.client.service.sdk.floor.FloorServiceImpl;
import com.gowild.sdk.metadata.pb.SdkBothMsgProto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zh on 2018/2/27.
 */
@Receiver
public class HomeServiceImpl extends AbstractServiceImpl implements IHomeService{

    Logger logger = LoggerFactory.getLogger(FloorServiceImpl.class);

    @Override
    public void syncHomeResult(S_SYNC_HOME response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        logger.info("====== >>> SDK【同步】家庭返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void deleteHomeResult(S_DELETE_HOME response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        logger.info("====== >>> SDK【删除】家庭返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void addHomeResult(S_ADD_HOME response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        logger.info("====== >>> SDK【添加】家庭返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void updateHomeResult(S_UPDATE_HOME response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        logger.info("====== >>> SDK【更新】家庭返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void addHomeBatchResult(S_ADD_HOME_BATCH response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        logger.info("====== >>> SDK【批量添加】家庭返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void updateHomeBatchResult(S_UPDATE_HOME_BATCH response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        logger.info("====== >>> SDK【批量更新】家庭返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }
}
