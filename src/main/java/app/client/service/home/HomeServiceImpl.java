package app.client.service.home;

import app.client.net.protocol.response.sdk.home.S_ADD_HOME_RESULT;
import app.client.net.protocol.response.sdk.home.S_DELETE_HOME_RESULT;
import app.client.net.protocol.response.sdk.home.S_SYNC_HOME_RESULT;
import app.client.net.protocol.response.sdk.home.S_UPDATE_HOME_RESULT;
import app.client.service.AbstractServiceImpl;
import com.gowild.sdktcp.metadata.pb.SdkBothMsgProto;

/**
 * Created by zh on 2018/2/27.
 */
public class HomeServiceImpl extends AbstractServiceImpl implements IHomeService{

    @Override
    public void addHomeResult(S_ADD_HOME_RESULT response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【同步】主机绑定设备返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void deleteHomeResult(S_DELETE_HOME_RESULT response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【同步】主机绑定设备返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void updateHomeResult(S_UPDATE_HOME_RESULT response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【同步】主机绑定设备返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }

    @Override
    public void syncHomeResult(S_SYNC_HOME_RESULT response) {
        SdkBothMsgProto.SdkCommonResponseMsg msg = response.getCommonResponseMsg();
        System.out.println("====== >>> SDK【同步】主机绑定设备返回码是 : " + msg.getCode() + " | 描述：" + msg.getDesc());
    }
}
