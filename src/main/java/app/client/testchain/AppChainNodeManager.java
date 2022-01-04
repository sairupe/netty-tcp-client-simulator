package app.client.testchain;

import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.C_APP_HEART_BEAT;
import app.client.net.protocol.request.C_APP_LOGIN;
import app.client.net.task.TaskManager;
import app.client.net.task.app.AppHeartBeatTask;
import app.client.user.session.UserSession;
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

        // 心跳协议
        C_APP_HEART_BEAT heartBeat = ProtocolFactory.createRequestProtocol(C_APP_HEART_BEAT.class, userSession.getCtx());
        AppHeartBeatTask task = new AppHeartBeatTask(userSession.getCtx(), heartBeat);
        TaskManager.getInstance().addTickTask(task, 2, 50, TimeUnit.SECONDS);
    }

}
