package app.client.testchain;

import app.client.net.dispacher.DispacherManager;
import app.client.net.protocol.response.S_DEVICE_LOGIN_RESULT;
import app.client.net.protocol.response.sdk.device.S_ADD_DEVICE_RESULT;
import app.client.testchain.sdk.db.BaseDbInfoInsertNode;
import app.client.testchain.sdk.protocol.area.AddAreaCommandNode;
import app.client.testchain.sdk.protocol.device.SdkAddDeviceCommandNode;
import app.client.testchain.sdk.protocol.device.SdkGetXbBindAllMasterCommandNode;
import app.client.testchain.sdk.protocol.device.SdkLoginCommandNode;
import app.client.testchain.sdk.protocol.device.SdkSyncDeviceCommandNode;
import app.client.testchain.sdk.protocol.device.SimularCommandNode;
import app.client.testchain.sdk.protocol.floor.AddFloorCommandNode;
import app.client.testchain.sdk.protocol.home.AddHomeCommandNode;
import app.client.user.session.UserSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
//        startingChainNode.addLastNext(new SdkSyncDeviceCommandNode().registListenProtocol(S_DEVICE_LOGIN_RESULT.class));
        // 添加设备指令
        startingChainNode.addLastNext(new SdkAddDeviceCommandNode().registListenProtocol(S_DEVICE_LOGIN_RESULT.class));
        // 更新设备指令
//        startingChainNode.addLastNext(new SdkUpdateDeviceCommandNode().registListenProtocol(S_SYNC_DEVICE_RESULT.class));
        // 删除设备指令
//        startingChainNode.addLastNext(new SdkDeleteDeviceCommandNode().registListenProtocol(S_UPDATE_HOME_RESULT.class));


        // 添加家庭指令
        startingChainNode.addLastNext(new AddHomeCommandNode().registListenProtocol(S_DEVICE_LOGIN_RESULT.class));
        // 更新家庭指令
//        startingChainNode.addLastNext(new UpdateHomeCommandNode().registListenProtocol(S_ADD_HOME_RESULT.class));
        // 同步家庭指令
//        startingChainNode.addLastNext(new SyncHomeCommandNode().registListenProtocol(S_UPDATE_HOME_RESULT.class));
        // 删除家庭指令
//        startingChainNode.addLastNext(new DeleteHomeCommandNode().registListenProtocol(S_SYNC_HOME_RESULT.class));

        // 添加楼层指令
        startingChainNode.addLastNext(new AddFloorCommandNode().registListenProtocol(S_DEVICE_LOGIN_RESULT.class));
        // 更新楼层指令
//        startingChainNode.addLastNext(new UpdateFloorCommandNode().registListenProtocol(S_DEVICE_LOGIN_RESULT.class));
        // 同步楼层指令
//        startingChainNode.addLastNext(new SyncFloorCommandNode().registListenProtocol(S_DEVICE_LOGIN_RESULT.class));
        // 删除楼层指令
//        startingChainNode.addLastNext(new DeleteFloorCommandNode().registListenProtocol(S_DEVICE_LOGIN_RESULT.class));

        // 添加区域指令
        startingChainNode.addLastNext(new AddAreaCommandNode().registListenProtocol(S_DEVICE_LOGIN_RESULT.class));
//        // 更新区域指令
//        startingChainNode.addLastNext(new UpdateAreaCommandNode().registListenProtocol(S_DEVICE_LOGIN_RESULT.class));
//        // 同步区域指令
//        startingChainNode.addLastNext(new SyncAreaCommandNode().registListenProtocol(S_DEVICE_LOGIN_RESULT.class));
//        // 删除区域指令
//        startingChainNode.addLastNext(new DeleteAreaCommandNode().registListenProtocol(S_DEVICE_LOGIN_RESULT.class));

        // 模拟命令指令
        startingChainNode.addLastNext(new SimularCommandNode().registListenProtocol(S_ADD_DEVICE_RESULT.class));
        // 获取所有绑定设备指令
        startingChainNode.addLastNext(new SdkGetXbBindAllMasterCommandNode().registListenProtocol(S_DEVICE_LOGIN_RESULT.class));


//        startingChainNode.addLastNext(new SdkAddDeviceCommandNode().registListenProtocol(S_DEVICE_LOGIN_RESULT.class));

        DispacherManager.getInstance().setChainNode(startingChainNode);
        startingChainNode.start(userSession, con);

        // 心跳协议
//        C_DEVICE_HEART_BEAT heartBeat = ProtocolFactory.createRequestProtocol(C_DEVICE_HEART_BEAT.class,userSession.getCtx());
//        SdkDeviceHeartBeatTask task = new SdkDeviceHeartBeatTask(userSession.getCtx(), heartBeat);
//        TaskManager.getInstance().addTickTask(task, 2, 5, TimeUnit.SECONDS);
    }
}
