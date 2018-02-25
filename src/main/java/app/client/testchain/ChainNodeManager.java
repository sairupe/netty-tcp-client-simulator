package app.client.testchain;

import app.client.net.dispacher.DispacherManager;
import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.sdk.C_DEVICE_HEART_BEAT;
import app.client.net.protocol.response.S_DEVICE_LOGIN_RESULT;
import app.client.net.protocol.response.sdk.S_ADD_DEVICE_RESULT;
import app.client.net.task.SdkDeviceHeartBeatTask;
import app.client.net.task.TaskManager;
import app.client.testchain.sdk.db.BaseDbInfoInsertNode;
import app.client.testchain.sdk.protocol.SdkAddDeviceCommandNode;
import app.client.testchain.sdk.protocol.SdkDeleteDeviceCommandNode;
import app.client.testchain.sdk.protocol.SdkLoginCommandNode;
import app.client.testchain.sdk.protocol.SdkSyncDeviceCommandNode;
import app.client.testchain.sdk.protocol.SimularCommandNode;
import app.client.user.session.UserSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

/**
 * Created by zh on 2017/11/21.
 */
public class ChainNodeManager {

    private static ChainNodeManager chainNodeManager = new ChainNodeManager();
    private static Connection con;
    private ChainNodeManager() {

    }

    static {
        String urlstr = "jdbc:mysql://localhost:3306/gwsdk";
        String sql = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            System.err.print("classnotfoundexception :");
            System.err.print(e.getMessage());
        }
        try {
            con = DriverManager.getConnection(urlstr, "root", "123456");
        }catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("sqlexception :" + ex.getMessage());
            System.err.println("sql :" + sql);
        }
    }

    /**
     * 开始执行的任务节点
     */
    public IChainNode startingChainNode;

    public static ChainNodeManager getInstance(){
        return chainNodeManager;
    }

    public void start(UserSession userSession){
//        startingChainNode = new BaseDbInfoInsertNode();
//        startingChainNode.setVar(userSession, con);
//        startingChainNode.addLastNext(new XbLoginCommandNode());
//        startingChainNode.addLastNext(new SimularCommandNode().registListenProtocol(S_DEVICE_LOGIN_RESULT.class));
//
//        startingChainNode.start(userSession, con);
//        DispacherManager.getInstance().setChainNode(startingChainNode);
//
//        C_DEVICE_HEART_BEAT heartBeat = ProtocolFactory.createRequestProtocol(C_DEVICE_HEART_BEAT.class,
//                userSession.getCtx());
//        SdkDeviceHeartBeatTask task = new SdkDeviceHeartBeatTask(userSession.getCtx(), heartBeat);
//        TaskManager.getInstance().addTickTask(task, 2, 5, TimeUnit.SECONDS);

//        startingChainNode.addLastNext(new XbLoginCommandNode());

        // SDK模拟登陆
        startingChainNode = new BaseDbInfoInsertNode();
        startingChainNode.setVar(userSession, con);
        // 登录指令
        startingChainNode.addLastNext(new SdkLoginCommandNode());
        // 同步设备指令
        startingChainNode.addLastNext(new SdkSyncDeviceCommandNode().registListenProtocol(S_DEVICE_LOGIN_RESULT.class));
        // 添加设备指令
//        startingChainNode.addLastNext(new SdkAddDeviceCommandNode().registListenProtocol(S_DEVICE_LOGIN_RESULT.class));
        // 删除设备指令
//        startingChainNode.addLastNext(new SdkDeleteDeviceCommandNode().registListenProtocol(S_ADD_DEVICE_RESULT.class));


        // 模拟命令指令
        //startingChainNode.addLastNext(new SimularCommandNode().registListenProtocol(S_ADD_DEVICE_RESULT.class));
        DispacherManager.getInstance().setChainNode(startingChainNode);
        startingChainNode.start(userSession, con);
//        C_DEVICE_HEART_BEAT heartBeat = ProtocolFactory.createRequestProtocol(C_DEVICE_HEART_BEAT.class,userSession.getCtx());
//        SdkDeviceHeartBeatTask task = new SdkDeviceHeartBeatTask(userSession.getCtx(), heartBeat);
//        TaskManager.getInstance().addTickTask(task, 2, 5, TimeUnit.SECONDS);
    }
}
