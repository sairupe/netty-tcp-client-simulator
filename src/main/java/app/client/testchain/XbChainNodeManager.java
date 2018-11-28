package app.client.testchain;

import app.client.common.TimeRecordKey;
import app.client.data.DbConnecter;
import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.C_DOCKER_LOGIN;
import app.client.net.protocol.request.C_XB_HEART_BEAT;
import app.client.net.task.TaskManager;
import app.client.net.task.sdk.SdkDeviceHeartBeatTask;
import app.client.net.test.QuickStarter;
import app.client.testchain.db.BaseDbInfoInsertNode;
import app.client.testchain.sdk.SdkTestConst;
import app.client.testchain.sdk.protocol.area.AddAreaBatchDynamicTidCommandNode;
import app.client.testchain.sdk.protocol.device.SdkSyncDeviceDynamicTidCommandNode;
import app.client.testchain.sdk.protocol.floor.AddFloorBatchDynamicTidCommandNode;
import app.client.user.session.UserSession;
import app.client.utils.TokenUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by zh on 2017/11/21.
 */
public class XbChainNodeManager {

    private static final Logger logger = LoggerFactory.getLogger(XbChainNodeManager.class);

    /**
     * 开始执行的任务节点
     */
    public IChainNode startingChainNode;

    public void start(UserSession userSession) {

        // 数据库任务
        startingChainNode = new BaseDbInfoInsertNode();
        startingChainNode.setVar(userSession, DbConnecter.getRobotDbConncetion());
        String testMac = SdkTestConst.EMPTY_STRING;

        // 登录指令
        //    94:a1:a2:c0:47:c8
//        testMac = "94:a1:a2:c0:47:c8";

        // 2968  94:a1:a2:bf:b7:f0
//        testMac = "94:a1:a2:bf:b7:f0";

        // 2969  94:a1:a2:c0:37:2c
//        testMac = "94:a1:a2:c0:37:2c";

        // 2305  94:a1:a2:f4:2f:fd 老友炒粉 ------------  废弃
//        testMac = "94:a1:a2:f4:2f:fd";

        // 2306  94:a1:a2:f4:91:19 桂林米粉(DOCKER)
//        testMac = "94:a1:a2:f4:91:19";

        // 2307  94:a1:a2:bd:c5:c6 生榨米粉(DOCKER)
//        testMac = "94:a1:a2:bd:c5:c6";

        // 2308  94:a1:a2:bd:93:d2 老友粉(XB)
//        testMac = "94:a1:a2:bd:93:d2";

        // TEST LOGIN
//        testMac = "94:a1:a2:f4:5e:49";


        String token = null;
        if (QuickStarter.PRESS_TEST) {// 压测从数据库捞
            token = userSession.getRobotToken();
        } else {// 非压测直接HTTP请求
            if (StringUtils.isEmpty(testMac)) {
                logger.warn("测试的testMac为空，肯定无法登陆");
                System.exit(0);
            }
            token = TokenUtil.getRobotToken(testMac);
        }
        doXbOrDockerLogin(userSession, token);

        // 插座定时触发测试
//        RobotNotifyPluginTimeExecuteCommandNode notifyPluginTimeExecuteCommandNode
//                = new RobotNotifyPluginTimeExecuteCommandNode(1, mac2308);
//        startingChainNode.addLastNext(notifyPluginTimeExecuteCommandNode);

        // 智能家居压测动态数据
        AddFloorBatchDynamicTidCommandNode addFloor = new AddFloorBatchDynamicTidCommandNode();
        addFloor.setRotbotMac(userSession.getMac());
        AddAreaBatchDynamicTidCommandNode addArea = new AddAreaBatchDynamicTidCommandNode();
        addArea.setRobotMac(userSession.getMac());
        SdkSyncDeviceDynamicTidCommandNode syncDevice = new SdkSyncDeviceDynamicTidCommandNode();
        syncDevice.setRobotMac(userSession.getMac());

        startingChainNode.addLastNext(addFloor);
        startingChainNode.addLastNext(addArea);
        startingChainNode.addLastNext(syncDevice);


        //全量数据
//        startingChainNode.addLastNext(new AddHomeBatchCommandNode());
//        startingChainNode.addLastNext(new AddFloorBatchCommandNode());
//        startingChainNode.addLastNext(new AddAreaBatchCommandNode());
//        startingChainNode.addLastNext(new SdkSyncDeviceCommandNode());

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

        // 设置在用户session用，等待登录结果返回后再执行发送协议
        userSession.setChainNode(startingChainNode);

        // 心跳协议
        C_XB_HEART_BEAT heartBeat = ProtocolFactory.createRequestProtocol(C_XB_HEART_BEAT.class, userSession.getCtx());
        SdkDeviceHeartBeatTask task = new SdkDeviceHeartBeatTask(userSession.getCtx(), heartBeat);
        TaskManager.getInstance().addTickTask(task, 2, 20, TimeUnit.SECONDS);
    }

    // 小白或者DOCKER登陆
    private void doXbOrDockerLogin(UserSession userSession, String token) {
        C_DOCKER_LOGIN loginCmd = ProtocolFactory.createRequestProtocol(C_DOCKER_LOGIN.class,
                userSession.getCtx());
        loginCmd.setToken(token);
        userSession.sendMsg(loginCmd);
        userSession.markTimeStart(TimeRecordKey.XB_LOGIN_TIME);
    }
}
