package app.client.testchain;

import app.client.net.protocol.ProtocolFactory;
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
        String token2967 = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiZ293aWxkIl0sInVzZXJfbmFtZSI6Ijk0OmExOmEyOmMwOjQ3OmM4Iiwic2NvcGUiOlsicmVhZCIsIndyaXRlIiwidHJ1c3QiXSwiZXhwIjoxNTM2NDE0NDQxLCJhdXRob3JpdGllcyI6WyJST0xFX0FQUCJdLCJqdGkiOiI1NWIyOGIzZi1mNWFiLTQxZmItOGUxYS0wZDdlNzI2Y2ZmMTMiLCJjbGllbnRfaWQiOiJnb3dpbGRfYXBwX2NsaWVudCJ9.Ddgg3cFm7P6m2HEUM_ab9cath4kfyJDDAII7cmyhjgI";
//        startingChainNode.addLastNext(new DockerLoginCommandNode(mac2967, token2967));
        // 2968  94:a1:a2:bf:b7:f0
        String mac2968 = "94:a1:a2:bf:b7:f0";
        String token2968 = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiZ293aWxkIl0sInVzZXJfbmFtZSI6Ijk0OmExOmEyOmJmOmI3OmYwIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIiwidHJ1c3QiXSwiZXhwIjoxNTM2MzkxNTE0LCJhdXRob3JpdGllcyI6WyJST0xFX0FQUCJdLCJqdGkiOiI0NGJlYzI4ZC04MjU3LTRhMDctYjhkMC04NDUwYTkxNmQxYzYiLCJjbGllbnRfaWQiOiJnb3dpbGRfYXBwX2NsaWVudCJ9.Wk9I4sRw7fsXVVTAO-5-1G9SNUmuCi7uupP1KBDzWZk";
        startingChainNode.addLastNext(new DockerLoginCommandNode(mac2968, token2968));
        // 2969  94:a1:a2:c0:37:2c
        String mac2969 = "94:a1:a2:c0:37:2c";
        String token2969 = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiZ293aWxkIl0sInVzZXJfbmFtZSI6Ijk0OmExOmEyOmMwOjM3OjJjIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIiwidHJ1c3QiXSwiZXhwIjoxNTM2NDAxODk5LCJhdXRob3JpdGllcyI6WyJST0xFX0FQUCJdLCJqdGkiOiJjYzA4YjYwYy0wNjU3LTQ4N2UtODU1OS1hZDJmZWFkYjAwZmQiLCJjbGllbnRfaWQiOiJnb3dpbGRfYXBwX2NsaWVudCJ9.hxA_pLMc-UELuBBBSGfBHHPqVj7RlMT1qzDM1z7_tjg";
//        startingChainNode.addLastNext(new DockerLoginCommandNode(mac2969, token2969));

        // 2305  94:a1:a2:f4:2f:fd 老友炒粉
        String mac2305 = "94:a1:a2:f4:2f:fd";
        String token2305 = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiZ293aWxkIl0sInVzZXJfbmFtZSI6Ijk0OmExOmEyOmY0OjJmOmZkIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIiwidHJ1c3QiXSwiZXhwIjoxNTM2NDExNDUwLCJhdXRob3JpdGllcyI6WyJST0xFX0FQUCJdLCJqdGkiOiI0NTZlOTNiMC1lZTI3LTQ5NTktYjgyOS03YTc1MzYxODAwOTkiLCJjbGllbnRfaWQiOiJnb3dpbGRfYXBwX2NsaWVudCJ9.ZozBRS46tI6yK_jO9K4_RcBLeZAvRS4TmsDtqplVTV0";
//        startingChainNode.addLastNext(new DockerLoginCommandNode(mac2305, token2305));
        // 2306  94:a1:a2:f4:91:19 桂林米粉
        String mac2306 = "94:a1:a2:f4:91:19";
        String token2306 = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiZ293aWxkIl0sInVzZXJfbmFtZSI6Ijk0OmExOmEyOmY0OjkxOjE5Iiwic2NvcGUiOlsicmVhZCIsIndyaXRlIiwidHJ1c3QiXSwiZXhwIjoxNTM2NDExNDY2LCJhdXRob3JpdGllcyI6WyJST0xFX0FQUCJdLCJqdGkiOiI1NTJjOTE4Ni0zN2MwLTQ0NzctOGFmMi1kYmY1YzdlMThmYmEiLCJjbGllbnRfaWQiOiJnb3dpbGRfYXBwX2NsaWVudCJ9.jH2cmU3E9puXBzNUIBIjBs9K10WkBrTRi713freWUbc";
//        startingChainNode.addLastNext(new DockerLoginCommandNode(mac2306, token2306));
        // 2307  94:a1:a2:bd:c5:c6 生榨米粉
        String mac2307 = "94:a1:a2:bd:c5:c6";
        String token2307 = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiZ293aWxkIl0sInVzZXJfbmFtZSI6Ijk0OmExOmEyOmJkOmM1OmM2Iiwic2NvcGUiOlsicmVhZCIsIndyaXRlIiwidHJ1c3QiXSwiZXhwIjoxNTM2NDExNDgxLCJhdXRob3JpdGllcyI6WyJST0xFX0FQUCJdLCJqdGkiOiJkMDJhZTQ5ZC1hNzIyLTQ2ODgtYmU2NS05YjQ4YWY3OWI1OWUiLCJjbGllbnRfaWQiOiJnb3dpbGRfYXBwX2NsaWVudCJ9.jg5VuZ1dzHEe3biIg3hhQghs159Wug6Fa3mLSNDpqf8";
//        startingChainNode.addLastNext(new DockerLoginCommandNode(mac2307, token2307));




        //全量数据
//        startingChainNode.addLastNext(new AddHomeBatchCommandNode());
//        startingChainNode.addLastNext(new AddFloorBatchCommandNode());
//        startingChainNode.addLastNext(new AddAreaBatchCommandNode());
//        startingChainNode.addLastNext(new SdkAddDeviceBatchCommandNode());

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
