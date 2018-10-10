package app.client.data;

import app.client.common.Const;
import app.client.net.task.TaskManager;
import app.client.net.test.QuickStarter;
import app.client.service.sdk.area.AreaServiceImpl;
import app.client.user.session.UserSession;
import app.client.user.session.UserSessionManager;
import com.gowild.sdk.protocol.SdkMsgType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zh on 2018/9/4.
 */
public class StatisticHolder {

    private static final Logger logger = LoggerFactory.getLogger(StatisticHolder.class);

    private static AtomicInteger robotClientStartCount = new AtomicInteger(0);
    private static AtomicInteger robotClientMaxCount = new AtomicInteger(0);
    private static AtomicInteger robotClientCurrentCount = new AtomicInteger(0);
    private static AtomicInteger robotClientShutDownCount = new AtomicInteger(0);
    private static AtomicInteger robotHearBeatTickCount = new AtomicInteger(0);
    private static AtomicInteger robotHandShakeCount = new AtomicInteger(0);
    private static AtomicInteger robotSendLoginCount = new AtomicInteger(0);
    private static AtomicInteger robotRecvLoginCount = new AtomicInteger(0);
    private static AtomicInteger robotGetTokenCount = new AtomicInteger(0);

    private static AtomicInteger appClientStartCount = new AtomicInteger(0);
    private static AtomicInteger appClientMaxCount = new AtomicInteger(0);
    private static AtomicInteger appClientCurrentCount = new AtomicInteger(0);
    private static AtomicInteger appClientShutDownCount = new AtomicInteger(0);
    private static AtomicInteger appHearBeatTickCount = new AtomicInteger(0);
    private static AtomicInteger appHandShakeCount = new AtomicInteger(0);
    private static AtomicInteger appSendLoginCount = new AtomicInteger(0);
    private static AtomicInteger appRecvLoginCount = new AtomicInteger(0);
    private static AtomicInteger appGetTokenCount = new AtomicInteger(0);

    public static int incRobotClient(){
        robotClientStartCount.incrementAndGet();
        int newCount = robotClientCurrentCount.incrementAndGet();
        if(newCount > robotClientMaxCount.get()){
            robotClientMaxCount.incrementAndGet();
        }
        return newCount;
    }
    public static int incRobotHeartBeatCount(){
        return robotHearBeatTickCount.incrementAndGet();
    }
    public static int incRobotHandShakeCount(){ return robotHandShakeCount.incrementAndGet();}
    public static int incRobotSendLoginCount(){ return  robotSendLoginCount.incrementAndGet(); }
    public static int incRobotRecvLoginCount(){ return  robotRecvLoginCount.incrementAndGet(); }
    public static int incRobotGetTokenCount(){return robotGetTokenCount.incrementAndGet(); }


    public static int incAppClient(){
        appClientStartCount.incrementAndGet();
        int newCount = appClientCurrentCount.incrementAndGet();
        if(newCount > appClientMaxCount.get()){
            appClientMaxCount.incrementAndGet();
        }
        return newCount;
    }
    public static int incAppHeartBeatCount(){
        return  appHearBeatTickCount.incrementAndGet();
    }
    public static int incAppHandShakeCount(){ return appHandShakeCount.incrementAndGet();}
    public static int incAppSendLoginCount(){ return appSendLoginCount.incrementAndGet(); }
    public static int incAppRecvLoginCount(){ return appRecvLoginCount.incrementAndGet(); }
    public static int incAppGetTokenCount(){return appGetTokenCount.incrementAndGet(); }


    public static int decRobot(){
        robotClientShutDownCount.incrementAndGet();
        return robotClientCurrentCount.decrementAndGet();
    }
    public static int decApp(){
        appClientShutDownCount.incrementAndGet();
        return appClientCurrentCount.decrementAndGet();
    }

    public static int getRobotCount() {
        return robotClientCurrentCount.get();
    }
    public static int getAppCount() {
        return appClientCurrentCount.get();
    }


    public static void print() {

        // 检测登陆超时
        List<Long> loginOutTimeList = new ArrayList();
        long totalTime = 0;
        Map<Long, UserSession> channelId2SessionMap = UserSessionManager.getInstance().getUid2SessionMap();
        for(Map.Entry<Long, UserSession> entry : channelId2SessionMap.entrySet()){
            UserSession userSession = entry.getValue();
            long loginTime = userSession.getLoginTime();
            long receivLoginTime = userSession.getReceivLoginResultTime();
            if(receivLoginTime > 0){
                totalTime += (receivLoginTime - userSession.getLoginTime());
            }
            long fromTime = System.currentTimeMillis() - loginTime;
            long differTime = receivLoginTime - loginTime;
            if((fromTime > Const.RECONNECT_PERIOD && receivLoginTime == 0) || differTime > Const.RECONNECT_PERIOD){
                loginOutTimeList.add(userSession.getUid());
            }
        }
        for(Long uid : loginOutTimeList){
            UserSession userSession = UserSessionManager.getInstance().getUserSessionByUid(uid);
            UserSessionManager.getInstance().removeUserSessionByUid(uid);
            logger.info("===>>>客户端重连####  mac:{} // account:{}, ", userSession.getMac(), userSession.getAccount());
            if(userSession.getClientType() == SdkMsgType.XB_CLIENT_TYPE){
                QuickStarter.quickStartRobot(userSession.getMac(), userSession.getRobotId());
            } else if(userSession.getClientType() == SdkMsgType.APP_CLIENT_TYPE){
                QuickStarter.quickStartApp(userSession.getAccount(), userSession.getAccountId());
            }
        }


        final StringBuilder sb = new StringBuilder("======================>>>>>StatisticHolder{\n\n");

        // 计算平均登陆时间
        float avgLoginTime = -1;
        if(totalTime > 0){
            avgLoginTime = totalTime * 1.0f / channelId2SessionMap.size();
        }
        sb.append("avgLoginTime='").append(avgLoginTime).append(" ms \n\n");

        sb.append("robotClientStartCount='").append(robotClientStartCount.get()).append('\n');
        sb.append("robotClientMaxCount='").append(robotClientMaxCount.get()).append('\n');
        sb.append("robotClientCurrentCount='").append(robotClientCurrentCount.get()).append('\n');
        sb.append("robotClientShutDownCount='").append(robotClientShutDownCount.get()).append('\n');
        sb.append("robotHearBeatTickCount='").append(robotHearBeatTickCount.get()).append('\n');
        sb.append("robotHandShakeCount='").append(robotHandShakeCount.get()).append('\n');
        sb.append("robotSendLoginCount='").append(robotSendLoginCount.get()).append('\n');
        sb.append("robotRecvLoginCount='").append(robotRecvLoginCount.get()).append('\n');
        sb.append("robotGetTokenCount='").append(robotGetTokenCount.get()).append("\n\n");

        sb.append("appClientStartCount='").append(appClientStartCount.get()).append('\n');
        sb.append("appClientCurrentCount='").append(appClientCurrentCount.get()).append('\n');
        sb.append("appClientMaxCount='").append(appClientCurrentCount.get()).append('\n');
        sb.append("appClientShutDownCount='").append(appClientShutDownCount.get()).append('\n');
        sb.append("appHearBeatTickCount='").append(appHearBeatTickCount.get()).append('\n');
        sb.append("appHandShakeCount='").append(appHandShakeCount.get()).append('\n');
        sb.append("appSendLoginCount='").append(appSendLoginCount.get()).append('\n');
        sb.append("appRecvLoginCount='").append(appRecvLoginCount.get()).append('\n');
        sb.append("appGetTokenCount='").append(appGetTokenCount.get()).append("\n\n");

        sb.append("requestQueueCount='").append(TaskManager.getInstance().getRequestQueueSize()).append('\n');
        sb.append("responseQueueCount='").append(TaskManager.getInstance().getResponseQueueSize()).append('\n');

        sb.append('}');
        logger.info(sb.toString());
    }

}
