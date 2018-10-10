package app.client.service.user;

import app.client.data.StatisticHolder;
import app.client.net.annotation.Receiver;
import app.client.net.protocol.response.S_APP_HEART_BEAT;
import app.client.net.protocol.response.S_APP_LOGIN;
import app.client.net.protocol.response.S_XB_CHAT;
import app.client.net.protocol.response.S_XB_CHAT_V2;
import app.client.net.protocol.response.S_XB_HEART_BEAT;
import app.client.net.protocol.response.S_XB_LOGIN;
import app.client.net.protocol.response.S_XB_REV_KEDA;
import app.client.net.protocol.response.S_XB_SEMANTIC_FINISH;
import app.client.service.AbstractServiceImpl;
import com.gowild.xb.tcp.proto.ChatMsgProto;
import com.gowild.xbtcp.metadata.pb.BaseBothMsgProto;
import com.gowild.xbtcp.metadata.pb.ChatS2XCMsgProto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author syriana.zh
 *         <p>
 *         2016年4月21日 下午3:21:43
 */
@Receiver
public class UserServiceImpl extends AbstractServiceImpl implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public void receivedAppHeartBeatResponse(S_APP_HEART_BEAT response) {
        StatisticHolder.incAppHeartBeatCount();
        logger.info("====== >>> APP设备收到返回时间====");
    }

    @Override
    public void receivedXbHeartBeatResponse(S_XB_HEART_BEAT response) {
        StatisticHolder.incRobotHeartBeatCount();
//        logger.info("====== >>> XB设备收到返回时间====");
    }

    @Override
    public void receivedXbChatResponse(S_XB_CHAT response) {
        ChatMsgProto.ChatMsg chatMsg = response.getChatMsg();
        String text = chatMsg.getReply();
        String expression = chatMsg.getExpression();
        String mood = chatMsg.getMood();
        logger.info("====== >>> XB收到闲聊，text:" + text + " | expression: " + expression + " | mood: " + mood);
    }

    @Override
    public void receivedXbChatV2Response(S_XB_CHAT_V2 response) {
        String reply = response.getChatMsg().getReply();
        logger.info("====== >>> XB收到V2闲聊，text:" + reply);
    }

    @Override
    public void receivedXbLoginRes(S_XB_LOGIN response) {
        BaseBothMsgProto.BaseResponse loginResult = response.getLoginResult();
        int code = loginResult.getCode();
        String desc = loginResult.getDesc();
        StatisticHolder.incRobotRecvLoginCount();
        response.getUserSession().setReceivLoginResultTime(System.currentTimeMillis());
//        logger.info("====== >>> XB登陆结果，code:" + code + " | desc: " + desc);
    }

    @Override
    public void receivedAppLoginRes(S_APP_LOGIN response) {
        BaseBothMsgProto.BaseResponse loginResult = response.getLoginResult();
        int code = loginResult.getCode();
        String desc = loginResult.getDesc();
        StatisticHolder.incAppRecvLoginCount();
        response.getUserSession().setReceivLoginResultTime(System.currentTimeMillis());
        logger.info("====== >>> APP登陆结果，code:" + code + " | desc: " + desc);
    }

    @Override
    public void receivedKedaRes(S_XB_REV_KEDA response) {
        ChatS2XCMsgProto.SendResponseForReceivedKeDa sendResponseForReceivedKeDa = response.getSendResponseForReceivedKeDa();
        String msgId = sendResponseForReceivedKeDa.getMsgId();
        logger.info("====== >>> 收到科大返回，msgId:{}", msgId);
    }

    @Override
    public void receivedSemanticFinishRes(S_XB_SEMANTIC_FINISH response) {
        ChatS2XCMsgProto.SemanticComplete semanticComplete = response.getSemanticComplete();
        String msgId = semanticComplete.getMsgId();
        logger.info("====== >>> 收到语义完成解析，msgId:{}", msgId);
    }
}
