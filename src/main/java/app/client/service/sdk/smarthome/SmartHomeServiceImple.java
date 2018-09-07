package app.client.service.sdk.smarthome;

import app.client.net.annotation.Receiver;
import app.client.net.protocol.response.smarthome.S_QUERY_ROBOT_COLLA_RES;
import app.client.service.AbstractServiceImpl;
import app.client.service.sdk.floor.FloorServiceImpl;
import com.gowild.smarthome.metadata.pb.SmartHomeS2ACMsgProto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zh on 2018/2/27.
 */
@Receiver
public class SmartHomeServiceImple extends AbstractServiceImpl implements ISmartHomeService {

    Logger logger = LoggerFactory.getLogger(FloorServiceImpl.class);

    @Override
    public void queryRobotColla(S_QUERY_ROBOT_COLLA_RES response) {
        SmartHomeS2ACMsgProto.SmartHomeQueryRobotCollaResMsg queryRobotCollaResMsg = response.getQueryRobotCollaResMsg();
        SmartHomeS2ACMsgProto.SmartHomeResponseMsg commonRes = queryRobotCollaResMsg.getCommonRes();
        Boolean binded = queryRobotCollaResMsg.getBinded();
        String bindCollaUsrId = queryRobotCollaResMsg.getBindCollaUsrId();
        Integer bindCompanyId = queryRobotCollaResMsg.getBindCompanyId();
        logger.info("====== >>> SMARTHOME【查询机器绑定第三方厂商信息】场景返回码是 : " + commonRes.getCode() + " | 描述：" + commonRes.getDesc());
        logger.info("====== >>> SMARTHOME【查询机器绑定第三方厂商信息】bined: " + binded +
                " | bindCollaUsrId：" + bindCollaUsrId + " | bindCompanyId：" + bindCompanyId);
    }
}
