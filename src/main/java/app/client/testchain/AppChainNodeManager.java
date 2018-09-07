package app.client.testchain;

import app.client.data.DbConnecter;
import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.C_APP_HEART_BEAT;
import app.client.net.protocol.request.C_APP_LOGIN;
import app.client.net.task.TaskManager;
import app.client.net.task.app.AppHeartBeatTask;
import app.client.testchain.db.BaseDbInfoInsertNode;
import app.client.testchain.smarthome.AppQueryRobotCollaCommandNode;
import app.client.user.session.UserSession;
import app.client.utils.CommonUtil;
import app.client.utils.TokenUtil;

import java.util.concurrent.TimeUnit;

/**
 * Created by zh on 2017/11/21.
 */
public class AppChainNodeManager {

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

        // 数据库任务
        startingChainNode = new BaseDbInfoInsertNode();
        startingChainNode.setVar(userSession, DbConnecter.getRobotDbConncetion());

        // 登录指令
        //    94:a1:a2:c0:47:c8
//        String account70 = "18617166985";
//        String token70 = TokenUtil.getAppToken(account70);
//        doAppLogin(userSession, token70);

//        String account9 = "15814786286";
//        String token9 = TokenUtil.getAppToken(account9);
//        doAppLogin(userSession, token9);
//        CommonUtil.threadPause(3000);
//
//        startingChainNode.addLastNext(new AppQueryRobotCollaCommandNode(17773));


        String token = userSession.getAppToken();
        doAppLogin(userSession, token);

        // 心跳协议
        C_APP_HEART_BEAT heartBeat = ProtocolFactory.createRequestProtocol(C_APP_HEART_BEAT.class, userSession.getCtx());
        AppHeartBeatTask task = new AppHeartBeatTask(userSession.getCtx(), heartBeat);
        TaskManager.getInstance().addTickTask(task, 2, 50, TimeUnit.SECONDS);

        startingChainNode.start();
    }

    // APP登陆
    private void doAppLogin(UserSession userSession, String token){
        C_APP_LOGIN loginCmd = ProtocolFactory.createRequestProtocol(C_APP_LOGIN.class,
                userSession.getCtx());
        loginCmd.setToken(token);
        userSession.sendMsg(loginCmd);
    }
}
