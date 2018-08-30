package app.client.testchain;

import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.C_DOCKER_LOGIN;
import app.client.net.protocol.request.C_XB_HEART_BEAT;
import app.client.net.task.sdk.SdkDeviceHeartBeatTask;
import app.client.net.task.TaskManager;
import app.client.testchain.sdk.db.BaseDbInfoInsertNode;
import app.client.testchain.sdk.protocol.area.AddAreaBatchCommandNode;
import app.client.testchain.sdk.protocol.area.DeleteAreaCommandNode;
import app.client.testchain.sdk.protocol.area.SyncAreaCommandNode;
import app.client.testchain.sdk.protocol.area.UpdateAreaBatchCommandNode;
import app.client.testchain.sdk.protocol.area.UpdateAreaBindFloorBatchCommandNode;
import app.client.testchain.sdk.protocol.device.SdkAddDeviceBatchCommandNode;
import app.client.testchain.sdk.protocol.device.SdkDeleteDeviceCommandNode;
import app.client.testchain.sdk.protocol.device.SdkSyncDeviceCommandNode;
import app.client.testchain.sdk.protocol.device.SdkUpdateDeviceBatchCommandNode;
import app.client.testchain.sdk.protocol.device.SdkUpdateDeviceBindAreaBatchCommandNode;
import app.client.testchain.sdk.protocol.device.SdkUpdateDeviceBindSceneBatchCommandNode;
import app.client.testchain.sdk.protocol.docker.Docker2LoginCommandNode;
import app.client.testchain.sdk.protocol.docker.DockerLoginCommandNode;
import app.client.testchain.sdk.protocol.floor.AddFloorBatchCommandNode;
import app.client.testchain.sdk.protocol.floor.DeleteFloorCommandNode;
import app.client.testchain.sdk.protocol.floor.SyncFloorCommandNode;
import app.client.testchain.sdk.protocol.floor.UpdateFloorBatchCommandNode;
import app.client.testchain.sdk.protocol.floor.UpdateFloorBindHomeBatchCommandNode;
import app.client.testchain.sdk.protocol.home.AddHomeBatchCommandNode;
import app.client.testchain.sdk.protocol.home.DeleteHomeCommandNode;
import app.client.testchain.sdk.protocol.home.SyncHomeCommandNode;
import app.client.testchain.sdk.protocol.home.UpdateHomeBatchCommandNode;
import app.client.testchain.sdk.protocol.scene.AddSceneBatchCommandNode;
import app.client.testchain.sdk.protocol.scene.DeleteSceneCommandNode;
import app.client.testchain.sdk.protocol.scene.SyncSceneCommandNode;
import app.client.testchain.sdk.protocol.scene.UpdateSceneBatchCommandNode;
import app.client.testchain.sdk.protocol.xb.XbLoginCommandNode;
import app.client.user.session.UserSession;
import app.client.utils.CommonUtil;

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
//        startingChainNode.addLastNext(new DockerLoginCommandNode());
//        startingChainNode.addLastNext(new SimularCommandNode().registListenProtocol(S_DEVICE_LOGIN_RESULT.class));
//
//        startingChainNode.start(userSession, con);
//        DispacherManager.getInstance().setChainNode(startingChainNode);
//
//        C_DEVICE_HEART_BEAT heartBeat = ProtocolFactory.createRequestProtocol(C_DEVICE_HEART_BEAT.class,
//                userSession.getCtx());
//        SdkDeviceHeartBeatTask task = new SdkDeviceHeartBeatTask(userSession.getCtx(), heartBeat);
//        TaskManager.getInstance().addTickTask(task, 2, 5, TimeUnit.SECONDS);

//        startingChainNode.addLastNext(new DockerLoginCommandNode());

        // SDK模拟登陆
        startingChainNode = new BaseDbInfoInsertNode();
        startingChainNode.setVar(userSession, con);
        // 登录指令
//        startingChainNode.addLastNext(new XbLoginCommandNode());
//        startingChainNode.addLastNext(new DockerLoginCommandNode());
//        startingChainNode.addLastNext(new Docker2LoginCommandNode());

        //    94:a1:a2:c0:47:c8
        String mac2967 = "94:a1:a2:c0:47:c8";
//        startingChainNode.addLastNext(new DockerLoginCommandNode(mac2967));
        C_DOCKER_LOGIN login2967 = ProtocolFactory.createRequestProtocol(C_DOCKER_LOGIN.class,
                userSession.getCtx());
        login2967.setMac(mac2967);
        userSession.sendMsg(login2967);

        // 2968  94:a1:a2:bf:b7:f0
        String mac2968 = "94:a1:a2:bf:b7:f0";
//        startingChainNode.addLastNext(new DockerLoginCommandNode(mac2968));
        C_DOCKER_LOGIN login2968 = ProtocolFactory.createRequestProtocol(C_DOCKER_LOGIN.class,
                userSession.getCtx());
        login2968.setMac(mac2968);
        userSession.sendMsg(login2968);

        // 2969  94:a1:a2:c0:37:2c
        String mac2969 = "94:a1:a2:c0:37:2c";
//        startingChainNode.addLastNext(new DockerLoginCommandNode(mac2969));
        C_DOCKER_LOGIN login2969 = ProtocolFactory.createRequestProtocol(C_DOCKER_LOGIN.class,
                userSession.getCtx());
        login2969.setMac(mac2969);
        userSession.sendMsg(login2969);

        // 2305  94:a1:a2:f4:2f:fd 老友炒粉 ------------  废弃
        String mac2305 = "94:a1:a2:f4:2f:fd";
//        startingChainNode.addLastNext(new DockerLoginCommandNode(mac2305));
        C_DOCKER_LOGIN login2305 = ProtocolFactory.createRequestProtocol(C_DOCKER_LOGIN.class,
                userSession.getCtx());
        login2305.setMac(mac2305);
        userSession.sendMsg(login2305);

        // 2306  94:a1:a2:f4:91:19 桂林米粉(DOCKER)
        String mac2306 = "94:a1:a2:f4:91:19";
//        startingChainNode.addLastNext(new DockerLoginCommandNode(mac2306));
        C_DOCKER_LOGIN login2306 = ProtocolFactory.createRequestProtocol(C_DOCKER_LOGIN.class,
                userSession.getCtx());
        login2306.setMac(mac2306);
        userSession.sendMsg(login2306);

        // 2307  94:a1:a2:bd:c5:c6 生榨米粉(DOCKER)
        String mac2307 = "94:a1:a2:bd:c5:c6";
//        startingChainNode.addLastNext(new DockerLoginCommandNode(mac2307));
        C_DOCKER_LOGIN login2307 = ProtocolFactory.createRequestProtocol(C_DOCKER_LOGIN.class,
                userSession.getCtx());
        login2307.setMac(mac2307);
        userSession.sendMsg(login2307);

        // 2308  94:a1:a2:bd:93:d2 老友粉(XB)
        String mac2308 = "94:a1:a2:bd:93:d2";
        //startingChainNode.addLastNext(new DockerLoginCommandNode(mac2308));
        C_DOCKER_LOGIN login2308 = ProtocolFactory.createRequestProtocol(C_DOCKER_LOGIN.class,
                userSession.getCtx());
        login2308.setMac(mac2308);
        userSession.sendMsg(login2308);

        CommonUtil.threadPause(1000);

        // 测试
//        String macTest = "94:a1:a2:bf:99:aa";
//        String tokenTest = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiZ293aWxkIl0sInVzZXJfbmFtZSI6Ijk0OmExOmEyOmJmOjk5OmFhIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIiwidHJ1c3QiXSwiZXhwIjoxNTM2MTUwMTg1LCJhdXRob3JpdGllcyI6WyJST0xFX0FQUCJdLCJqdGkiOiIwZWZlMTRiMC0yYTI3LTQ3ODUtODA2MC05YzhiY2UzNWFiNmUiLCJjbGllbnRfaWQiOiJnb3dpbGRfYXBwX2NsaWVudCJ9.paP4Hr8AirwoJthcIWeYmEe_jKIaPJhVSVte0C0qyU0";
//        startingChainNode.addLastNext(new DockerLoginCommandNode(macTest, tokenTest));

//        String macOldTest = "94:a1:a2:bf:99:aa";
//        String tokenOldTest = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiZ293aWxkIl0sInVzZXJfbmFtZSI6Ijk0OmExOmEyOmJmOjk5OmFhIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIiwidHJ1c3QiXSwiZXhwIjoxNTM2MTUwMTg1LCJhdXRob3JpdGllcyI6WyJST0xFX0FQUCJdLCJqdGkiOiIwZWZlMTRiMC0yYTI3LTQ3ODUtODA2MC05YzhiY2UzNWFiNmUiLCJjbGllbnRfaWQiOiJnb3dpbGRfYXBwX2NsaWVudCJ9.paP4Hr8AirwoJthcIWeYmEe_jKIaPJhVSVte0C0qyU0";
//        startingChainNode.addLastNext(new XbLoginCommandNode(macOldTest));


        //全量数据
        startingChainNode.addLastNext(new AddHomeBatchCommandNode());
        startingChainNode.addLastNext(new AddFloorBatchCommandNode());
        startingChainNode.addLastNext(new AddAreaBatchCommandNode());
        startingChainNode.addLastNext(new SdkAddDeviceBatchCommandNode());

        // 同步设备指令
//        startingChainNode.addLastNext(new SdkSyncDeviceCommandNode());
        // 添加设备指令
//        startingChainNode.addLastNext(new SdkAddDeviceBatchCommandNode());
        // 更新设备指令
//        startingChainNode.addLastNext(new SdkUpdateDeviceBatchCommandNode());
        // 删除设备指令
//        startingChainNode.addLastNext(new SdkDeleteDeviceCommandNode());

        // 添加家庭指令
//        startingChainNode.addLastNext(new AddHomeBatchCommandNode());
        // 更新家庭指令
//        startingChainNode.addLastNext(new UpdateHomeBatchCommandNode());
        // 同步家庭指令
//        startingChainNode.addLastNext(new SyncHomeCommandNode());
        // 删除家庭指令
//        startingChainNode.addLastNext(new DeleteHomeCommandNode());

        // 添加楼层指令
//        startingChainNode.addLastNext(new AddFloorBatchCommandNode());
        // 更新楼层指令
//        startingChainNode.addLastNext(new UpdateFloorBatchCommandNode());
        // 同步楼层指令
//        startingChainNode.addLastNext(new SyncFloorCommandNode());
        // 删除楼层指令
//        startingChainNode.addLastNext(new DeleteFloorCommandNode());

        // 添加区域指令
//        startingChainNode.addLastNext(new AddAreaBatchCommandNode());
//        // 更新区域指令
//        startingChainNode.addLastNext(new UpdateAreaBatchCommandNode());
//        // 同步区域指令
//        startingChainNode.addLastNext(new SyncAreaCommandNode());
//        // 删除区域指令
//        startingChainNode.addLastNext(new DeleteAreaCommandNode());

        // 添加场景指令
//        startingChainNode.addLastNext(new AddSceneBatchCommandNode());
        // 更新场景指令
//        startingChainNode.addLastNext(new UpdateSceneBatchCommandNode());
        // 同步场景指令
//        startingChainNode.addLastNext(new SyncSceneCommandNode());
        // 删除场景指令
//        startingChainNode.addLastNext(new DeleteSceneCommandNode());

        // =============== 测试绑定关系 =====================================
        // 修改设备绑定区域
//        startingChainNode.addLastNext(new SdkUpdateDeviceBindAreaBatchCommandNode());

        // 修改设备绑定场景
//        startingChainNode.addLastNext(new SdkUpdateDeviceBindSceneBatchCommandNode());

        // 修改区域绑定楼层
//        startingChainNode.addLastNext(new UpdateAreaBindFloorBatchCommandNode());

        // 修改楼层绑定家庭
//        startingChainNode.addLastNext(new UpdateFloorBindHomeBatchCommandNode());

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
