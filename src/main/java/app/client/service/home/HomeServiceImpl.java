package app.client.service.home;

import app.client.net.annotation.Receiver;
import app.client.net.protocol.response.sdk.batch.home.S_ADD_HOME_BATCH;
import app.client.net.protocol.response.sdk.batch.home.S_DELETE_HOME;
import app.client.net.protocol.response.sdk.batch.home.S_SYNC_HOME;
import app.client.net.protocol.response.sdk.batch.home.S_UPDATE_HOME_BATCH;
import app.client.net.protocol.response.sdk.single.home.S_ADD_HOME;
import app.client.net.protocol.response.sdk.single.home.S_UPDATE_HOME;
import app.client.service.AbstractServiceImpl;
import com.gowild.sdk.metadata.pb.SdkBothMsgProto;

/**
 * Created by zh on 2018/2/27.
 */
@Receiver
public class HomeServiceImpl extends AbstractServiceImpl implements IHomeService{

    @Override
    public void syncHomeResult(S_SYNC_HOME response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【同步】家庭返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void deleteHomeResult(S_DELETE_HOME response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【删除】家庭返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void addHomeResult(S_ADD_HOME response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【添加】家庭返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void updateHomeResult(S_UPDATE_HOME response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【更新】家庭返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void addHomeBatchResult(S_ADD_HOME_BATCH response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【批量添加】家庭返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void updateHomeBatchResult(S_UPDATE_HOME_BATCH response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【批量更新】家庭返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }
}
