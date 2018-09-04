package app.client.service.user;

import app.client.net.annotation.Handler;
import app.client.net.annotation.Receiver;
import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.ResponseProtocol;
import app.client.net.protocol.request.*;
import app.client.net.protocol.request.sdk.batch.device.C_DEVICE_HEART_BEAT;
import app.client.net.protocol.request.sdk.batch.device.C_DEVICE_LOGIN;
import app.client.net.protocol.response.S_APP_HEART_BEAT;
import app.client.net.protocol.response.S_APP_LOGIN;
import app.client.net.protocol.response.S_XB_CHAT;
import app.client.net.protocol.response.S_XB_HEART_BEAT;
import app.client.net.protocol.response.S_XB_LOGIN;
import app.client.net.task.app.AppHeartBeatTask;
import app.client.net.task.sdk.SdkDeviceHeartBeatTask;
import app.client.net.task.TaskManager;
import app.client.net.task.xb.XbHeartBeatTask;
import app.client.service.AbstractServiceImpl;
import app.client.service.device.DeviceServiceImpl;
import app.client.user.session.UserSession;
import com.gowild.core.util.HttpUtil;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.xb.tcp.proto.ChatMsgProto;
import com.gowild.xbtcp.metadata.pb.BaseBothMsgProto;
import org.json.JSONObject;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author syriana.zh
 *         <p>
 *         2016年4月21日 下午3:21:43
 */
@Receiver
public class UserServiceImpl extends AbstractServiceImpl implements IUserService {

    @Override
    public void receivedAppHeartBeatResponse(S_APP_HEART_BEAT response) {
        System.out.println("====== >>> APP设备收到返回时间====");
    }

    @Override
    public void receivedXbHeartBeatResponse(S_XB_HEART_BEAT response) {
        System.out.println("====== >>> XB设备收到返回时间====");
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
        System.out.println("====== >>> XB登陆结果，code:" + code + " | desc: " + desc);
    }

    @Override
    public void receivedAppLoginRes(S_APP_LOGIN response) {
        BaseBothMsgProto.BaseResponse loginResult = response.getLoginResult();
        int code = loginResult.getCode();
        String desc = loginResult.getDesc();
        System.out.println("====== >>> APP登陆结果，code:" + code + " | desc: " + desc);
    }
}
