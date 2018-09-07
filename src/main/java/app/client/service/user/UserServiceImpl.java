package app.client.service.user;

import app.client.data.StatisticHolder;
import app.client.net.annotation.Receiver;
import app.client.net.protocol.response.S_APP_HEART_BEAT;
import app.client.net.protocol.response.S_APP_LOGIN;
import app.client.net.protocol.response.S_XB_CHAT;
import app.client.net.protocol.response.S_XB_HEART_BEAT;
import app.client.net.protocol.response.S_XB_LOGIN;
import app.client.service.AbstractServiceImpl;
import com.gowild.xb.tcp.proto.ChatMsgProto;
import com.gowild.xbtcp.metadata.pb.BaseBothMsgProto;

/**
 * @author syriana.zh
 *         <p>
 *         2016年4月21日 下午3:21:43
 */
@Receiver
public class UserServiceImpl extends AbstractServiceImpl implements IUserService {

    @Override
    public void receivedAppHeartBeatResponse(S_APP_HEART_BEAT response) {
        StatisticHolder.incAppHeartBeatCount();
        System.out.println("====== >>> APP设备收到返回时间====");
    }

    @Override
    public void receivedXbHeartBeatResponse(S_XB_HEART_BEAT response) {
        StatisticHolder.incRobotHeartBeatCount();
//        System.out.println("====== >>> XB设备收到返回时间====");
    }

    @Override
    public void receivedXbChatResponse(S_XB_CHAT response) {
        ChatMsgProto.ChatMsg chatMsg = response.getChatMsg();
        String text = chatMsg.getReply();
        String expression = chatMsg.getExpression();
        String mood = chatMsg.getMood();
        System.out.println("====== >>> XB收到闲聊，text:" + text + " | expression: " + expression + " | mood: " + mood);
    }

    @Override
    public void receivedXbLoginRes(S_XB_LOGIN response) {
        BaseBothMsgProto.BaseResponse loginResult = response.getLoginResult();
        int code = loginResult.getCode();
        String desc = loginResult.getDesc();
        StatisticHolder.incRobotRecvLoginCount();
//        System.out.println("====== >>> XB登陆结果，code:" + code + " | desc: " + desc);
    }

    @Override
    public void receivedAppLoginRes(S_APP_LOGIN response) {
        BaseBothMsgProto.BaseResponse loginResult = response.getLoginResult();
        int code = loginResult.getCode();
        String desc = loginResult.getDesc();
        StatisticHolder.incAppRecvLoginCount();
        System.out.println("====== >>> APP登陆结果，code:" + code + " | desc: " + desc);
    }
}
