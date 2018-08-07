package app.client.service.user;

import app.client.net.annotation.Handler;
import app.client.net.annotation.Receiver;
import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.ResponseProtocol;
import app.client.net.protocol.request.*;
import app.client.net.protocol.request.sdk.batch.device.C_DEVICE_HEART_BEAT;
import app.client.net.protocol.request.sdk.batch.device.C_DEVICE_LOGIN;
import app.client.net.protocol.response.S_APP_HEART_BEAT;
import app.client.net.protocol.response.S_XB_HEART_BEAT;
import app.client.net.task.app.AppHeartBeatTask;
import app.client.net.task.sdk.SdkDeviceHeartBeatTask;
import app.client.net.task.TaskManager;
import app.client.net.task.xb.XbHeartBeatTask;
import app.client.service.AbstractServiceImpl;
import app.client.service.device.DeviceServiceImpl;
import app.client.user.session.UserSession;
import com.gowild.core.util.HttpUtil;
import com.gowild.sdk.protocol.SdkMsgType;
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

    @Resource
    DeviceServiceImpl deviceServiceImpl;

    @Override
    public void userLogin(UserSession userSession) {
        C_DEVICE_LOGIN login = ProtocolFactory.createRequestProtocol(C_DEVICE_LOGIN.class,
                userSession.getCtx());
        login.setUniqueCode("12315");
        login.setDeviceType(12315);
        login.setDeviceId("12315");
        login.setLoginTime(12315);
        login.setDeviceSn("12315");
        login.setEncrypCode("12315");

        userSession.sendMsg(login);

        C_DEVICE_HEART_BEAT heartBeat = ProtocolFactory.createRequestProtocol(C_DEVICE_HEART_BEAT.class,
                userSession.getCtx());
        SdkDeviceHeartBeatTask task = new SdkDeviceHeartBeatTask(userSession.getCtx(), heartBeat);
        TaskManager.getInstance().addTickTask(task, 2, 30, TimeUnit.SECONDS);

    }

    @Override
    @Handler(moduleId = SdkMsgType.APP_CLIENT_TYPE, sequenceId = 106)
    public void receivedAppHeartBeatResponse(S_APP_HEART_BEAT response) {
        System.out.println("====== >>> APP设备收到返回时间====");
    }

    @Override
    @Handler(moduleId = SdkMsgType.XB_CLIENT_TYPE, sequenceId = 110)
    public void receivedXbHeartBeatResponse(S_XB_HEART_BEAT response) {
        C_MACHINE_INFO_UPDATE info = ProtocolFactory.createRequestProtocol(C_MACHINE_INFO_UPDATE.class, response.getUserSession().getCtx());
        info.setRobotPower((byte) 88);
        response.getUserSession().sendMsg(info);
        System.out.println("====== >>> XB设备收到返回时间====");
    }

    @Override
    public void createUser(UserSession userSession) {
    }

    @Override
    public void receivedCreateUserResponse(ResponseProtocol response) {
    }

    @Override
    public void selectUser(long userId) {
    }

    @Override
    public void receivedSelectUser(ResponseProtocol response) {
    }

    @Override
    public void xbLogin(UserSession userSession) {
        C_XB_LOGIN xbLogin = ProtocolFactory.createRequestProtocol(C_XB_LOGIN.class, userSession.getCtx());
        //xbLogin.setMac("94:a1:a2:f3:ec:51");//94:a1:a2:67:96:ce
        xbLogin.setMac("94:a1:a2:c0:47:c8");
        xbLogin.setSn("");

        xbLogin.setVersion("2.1.2");
        xbLogin.setVersionV2("100");

        xbLogin.setbVersion("119");
        xbLogin.setGifV1("157");
        xbLogin.setLoginIp("116.24.65.206");

        userSession.sendMsg(xbLogin);

        C_XB_HEART_BEAT heartBeat = ProtocolFactory.createRequestProtocol(C_XB_HEART_BEAT.class, userSession.getCtx());
        XbHeartBeatTask task = new XbHeartBeatTask(userSession.getCtx(), heartBeat);
        TaskManager.getInstance().addTickTask(task, 5, 5, TimeUnit.SECONDS);
    }

    @Override
    public void appLogin(UserSession userSession) {
        // 先获取到TOKEN
//		// 下面第一段不行
//		String url = "http://localhost:9002/passport/oauth/token";
//		String content = "grant_type=password&username=13265430549&password=123456";
//		String contentType = "application/x-www-form-urlencoded";
//		String response1 = HttpUtil.sendWebRequestByPost(null, url, content, null, contentType, 5000);
//		System.out.println("response1 : " + response1);

        try {
            String url = "http://localhost:9002/passport/oauth/token";
            Map<String, String> header = new HashMap<>();
            header.put("Authorization", "Basic Z293aWxkX2FwcF9jbGllbnQ6U1VrZSQjODNqZGlzbClESl8uLDMyNA==");
            header.put("Cache-Control", "no-cache");
            Map<String, String> param = new HashMap<>();
            param.put("grant_type", "password");
            param.put("username", "13265430549");
            param.put("password", "123456");
            String repsonse2 = null;
            repsonse2 = HttpUtil.sendWebRequestByForm(null, url, header, param, null);
            System.out.println("repsonse2 : " + repsonse2);
            JSONObject result = new JSONObject(repsonse2);
            JSONObject data = result.getJSONObject("data");
            String loginToken = data.get("access_token").toString();

            //ID:746 PHONE:13265430549
            C_APP_LOGIN login = ProtocolFactory.createRequestProtocol(C_APP_LOGIN.class, userSession.getCtx());
            login.setToken(loginToken);
            userSession.sendMsg(login);

            C_APP_HEART_BEAT heartBeat = ProtocolFactory.createRequestProtocol(C_APP_HEART_BEAT.class, userSession.getCtx());
            AppHeartBeatTask task = new AppHeartBeatTask(userSession.getCtx(), heartBeat);
            TaskManager.getInstance().addTickTask(task, 2, 30, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
