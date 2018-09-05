package app.client.service.sdk.smarthome;

import app.client.net.annotation.Receiver;
import app.client.net.protocol.response.sdk.batch.scene.S_ADD_SCENE_BATCH;
import app.client.net.protocol.response.sdk.batch.scene.S_DELETE_SCENE;
import app.client.net.protocol.response.sdk.batch.scene.S_SYNC_SCENE;
import app.client.net.protocol.response.sdk.batch.scene.S_UPDATE_SCENE_BATCH;
import app.client.net.protocol.response.sdk.single.scene.S_ADD_SCENE;
import app.client.net.protocol.response.sdk.single.scene.S_UPDATE_SCENE;
import app.client.net.protocol.response.smarthome.S_QUERY_ROBOT_COLLA_RES;
import app.client.service.AbstractServiceImpl;
import com.gowild.sdk.metadata.pb.SdkBothMsgProto;
import com.gowild.smarthome.metadata.pb.SmartHomeS2ACMsgProto;

/**
 * Created by zh on 2018/2/27.
 */
@Receiver
public class SmartHomeServiceImple extends AbstractServiceImpl implements ISmartHomeService {

    @Override
    public void queryRobotColla(S_QUERY_ROBOT_COLLA_RES response) {
        SmartHomeS2ACMsgProto.SmartHomeQueryRobotCollaResMsg queryRobotCollaResMsg = response.getQueryRobotCollaResMsg();
        SmartHomeS2ACMsgProto.SmartHomeResponseMsg commonRes = queryRobotCollaResMsg.getCommonRes();
        Boolean binded = queryRobotCollaResMsg.getBinded();
        String bindCollaUsrId = queryRobotCollaResMsg.getBindCollaUsrId();
        Integer bindCompanyId = queryRobotCollaResMsg.getBindCompanyId();
        System.out.println("====== >>> SMARTHOME【查询机器绑定第三方厂商信息】场景返回码是 : " + commonRes.getCode() + " | 描述：" + commonRes.getDesc());
        System.out.println("====== >>> SMARTHOME【查询机器绑定第三方厂商信息】bined: " + binded +
                " | bindCollaUsrId：" + bindCollaUsrId + " | bindCompanyId：" + bindCompanyId);
    }
}
