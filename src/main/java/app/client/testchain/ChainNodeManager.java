package app.client.testchain;

import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.C_XB_HEART_BEAT;
import app.client.net.task.sdk.SdkDeviceHeartBeatTask;
import app.client.net.task.TaskManager;
import app.client.testchain.sdk.db.BaseDbInfoInsertNode;
import app.client.testchain.sdk.protocol.area.AddAreaCommandNode;
import app.client.testchain.sdk.protocol.area.DeleteAreaCommandNode;
import app.client.testchain.sdk.protocol.area.SyncAreaCommandNode;
import app.client.testchain.sdk.protocol.area.UpdateAreaBindFloorCommandNode;
import app.client.testchain.sdk.protocol.area.UpdateAreaCommandNode;
import app.client.testchain.sdk.protocol.device.SdkAddDeviceCommandNode;
import app.client.testchain.sdk.protocol.device.SdkDeleteDeviceCommandNode;
import app.client.testchain.sdk.protocol.device.SdkSyncDeviceCommandNode;
import app.client.testchain.sdk.protocol.device.SdkUpdateDeviceBindAreaCommandNode;
import app.client.testchain.sdk.protocol.device.SdkUpdateDeviceBindSceneCommandNode;
import app.client.testchain.sdk.protocol.device.SdkUpdateDeviceCommandNode;
import app.client.testchain.sdk.protocol.floor.AddFloorCommandNode;
import app.client.testchain.sdk.protocol.floor.DeleteFloorCommandNode;
import app.client.testchain.sdk.protocol.floor.SyncFloorCommandNode;
import app.client.testchain.sdk.protocol.floor.UpdateFloorBindHomeCommandNode;
import app.client.testchain.sdk.protocol.floor.UpdateFloorCommandNode;
import app.client.testchain.sdk.protocol.home.AddHomeCommandNode;
import app.client.testchain.sdk.protocol.home.DeleteHomeCommandNode;
import app.client.testchain.sdk.protocol.home.SyncHomeCommandNode;
import app.client.testchain.sdk.protocol.home.UpdateHomeCommandNode;
import app.client.testchain.sdk.protocol.scene.AddSceneCommandNode;
import app.client.testchain.sdk.protocol.scene.DeleteSceneCommandNode;
import app.client.testchain.sdk.protocol.scene.SyncSceneCommandNode;
import app.client.testchain.sdk.protocol.scene.UpdateSceneCommandNode;
import app.client.testchain.sdk.protocol.xb.XbLoginCommandNode;
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
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("sqlexception :" + ex.getMessage());
            System.err.println("sql :" + sql);
        }
    }

    /**
     * 开始执行的任务节点
     */
    public IChainNode startingChainNode;

    public static ChainNodeManager getInstance() {
        return chainNodeManager;
    }

    public void start(UserSession userSession) {
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
        startingChainNode.addLastNext(new XbLoginCommandNode());

        //全量数据
//        startingChainNode.addLastNext(new AddHomeCommandNode());
//        startingChainNode.addLastNext(new AddFloorCommandNode());
//        startingChainNode.addLastNext(new AddAreaCommandNode());
//        startingChainNode.addLastNext(new SdkAddDeviceCommandNode());

        // 同步设备指令
        startingChainNode.addLastNext(new SdkSyncDeviceCommandNode());
        // 添加设备指令
//        startingChainNode.addLastNext(new SdkAddDeviceCommandNode());
        // 更新设备指令
//        startingChainNode.addLastNext(new SdkUpdateDeviceCommandNode());
        // 删除设备指令
//        startingChainNode.addLastNext(new SdkDeleteDeviceCommandNode());

        // 添加家庭指令
//        startingChainNode.addLastNext(new AddHomeCommandNode());
        // 更新家庭指令
//        startingChainNode.addLastNext(new UpdateHomeCommandNode());
        // 同步家庭指令
//        startingChainNode.addLastNext(new SyncHomeCommandNode());
        // 删除家庭指令
//        startingChainNode.addLastNext(new DeleteHomeCommandNode());

        // 添加楼层指令
//        startingChainNode.addLastNext(new AddFloorCommandNode());
        // 更新楼层指令
//        startingChainNode.addLastNext(new UpdateFloorCommandNode());
        // 同步楼层指令
//        startingChainNode.addLastNext(new SyncFloorCommandNode());
        // 删除楼层指令
//        startingChainNode.addLastNext(new DeleteFloorCommandNode());

        // 添加区域指令
//        startingChainNode.addLastNext(new AddAreaCommandNode());
//        // 更新区域指令
//        startingChainNode.addLastNext(new UpdateAreaCommandNode());
//        // 同步区域指令
//        startingChainNode.addLastNext(new SyncAreaCommandNode());
//        // 删除区域指令
//        startingChainNode.addLastNext(new DeleteAreaCommandNode());

        // 添加场景指令
//        startingChainNode.addLastNext(new AddSceneCommandNode());
        // 更新场景指令
//        startingChainNode.addLastNext(new UpdateSceneCommandNode());
        // 同步场景指令
//        startingChainNode.addLastNext(new SyncSceneCommandNode());
        // 删除场景指令
//        startingChainNode.addLastNext(new DeleteSceneCommandNode());

        // =============== 测试绑定关系 =====================================
        // 修改设备绑定区域
//        startingChainNode.addLastNext(new SdkUpdateDeviceBindAreaCommandNode());

        // 修改设备绑定场景
//        startingChainNode.addLastNext(new SdkUpdateDeviceBindSceneCommandNode());

        // 修改区域绑定楼层
//        startingChainNode.addLastNext(new UpdateAreaBindFloorCommandNode());

        // 修改楼层绑定家庭
//        startingChainNode.addLastNext(new UpdateFloorBindHomeCommandNode());

        // 模拟命令指令(JSON格式要修改)
//        startingChainNode.addLastNext(new SimularCommandNode());
        // 获取所有绑定设备指令
//        startingChainNode.addLastNext(new SdkGetXbBindAllMasterCommandNode());


//        startingChainNode.addLastNext(new SdkAddDeviceCommandNode());

        startingChainNode.start(userSession, con);

        // 心跳协议
        C_XB_HEART_BEAT heartBeat = ProtocolFactory.createRequestProtocol(C_XB_HEART_BEAT.class, userSession.getCtx());
        SdkDeviceHeartBeatTask task = new SdkDeviceHeartBeatTask(userSession.getCtx(), heartBeat);
        TaskManager.getInstance().addTickTask(task, 2, 60, TimeUnit.SECONDS);
    }
}
