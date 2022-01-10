package app.client.testchain;

import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.C_APP_HEART_BEAT;
import app.client.net.protocol.request.C_APP_LOGIN;
import app.client.net.task.TaskManager;
import app.client.net.task.app.AppHeartBeatTask;
import app.client.user.session.UserSession;
import app.client.utils.MD5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by zh on 2017/11/21.
 */
public class AppChainNodeManager {

    private static final Logger logger = LoggerFactory.getLogger(AppChainNodeManager.class);

    private static AppChainNodeManager chainNodeManager = new AppChainNodeManager();

    private AppChainNodeManager() {

    }

    /**
     * 开始执行的任务节点
     */
    public IChainNode startingChainNode;

    public static AppChainNodeManager getInstance() {
        return chainNodeManager;
    }

    public void start(UserSession userSession) {
        C_APP_LOGIN loginCmd = ProtocolFactory.createRequestProtocol(C_APP_LOGIN.class,
                userSession.getCtx());
        userSession.sendMsg(loginCmd);
        userSession.setChainNode(startingChainNode);

        loginCmd.setTime((int) System.currentTimeMillis());
        loginCmd.setServer_id(100110001);
        loginCmd.setUser_name("test007");
        loginCmd.setPlatform_id("1");
        loginCmd.setSub_channel_id("1");
        loginCmd.setInfant(2);
        loginCmd.setDevice_imei("");
        loginCmd.setDevice_id("");
        loginCmd.setDevice_type("ios10.3");
        loginCmd.setSystem_type((byte) 1);
        loginCmd.setSign(MD5Utils.getMd5(loginCmd.getPlatform_id() + loginCmd.getUser_name() + "2" + loginCmd.getTime().toString() + "T!SiV4RSE&&OOUHK"));


        // 心跳协议
        C_APP_HEART_BEAT heartBeat = ProtocolFactory.createRequestProtocol(C_APP_HEART_BEAT.class, userSession.getCtx());
        AppHeartBeatTask task = new AppHeartBeatTask(userSession.getCtx(), heartBeat);
        TaskManager.getInstance().addTickTask(task, 1, 4, TimeUnit.SECONDS);
    }

}
