package app.client.data;

import app.client.net.task.TaskManager;
import app.client.service.sdk.area.AreaServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zh on 2018/9/4.
 */
public class StatisticHolder {

    private static final Logger logger = LoggerFactory.getLogger(StatisticHolder.class);

    private static AtomicInteger robotClientCount = new AtomicInteger(0);
    private static AtomicInteger robotHearBeatTickCount = new AtomicInteger(0);
    private static AtomicInteger robotHandShakeCount = new AtomicInteger(0);
    private static AtomicInteger robotSendLoginCount = new AtomicInteger(0);
    private static AtomicInteger robotRecvLoginCount = new AtomicInteger(0);
    private static AtomicInteger robotGetTokenCount = new AtomicInteger(0);

    private static AtomicInteger appClientCount = new AtomicInteger(0);
    private static AtomicInteger appHearBeatTickCount = new AtomicInteger(0);
    private static AtomicInteger appHandShakeCount = new AtomicInteger(0);
    private static AtomicInteger appSendLoginCount = new AtomicInteger(0);
    private static AtomicInteger appRecvLoginCount = new AtomicInteger(0);
    private static AtomicInteger appGetTokenCount = new AtomicInteger(0);

    public static int incRobotClient(){ return robotClientCount.incrementAndGet();}
    public static int incRobotHeartBeatCount(){
        return robotHearBeatTickCount.incrementAndGet();
    }
    public static int incRobotHandShakeCount(){ return robotHandShakeCount.incrementAndGet();}
    public static int incRobotSendLoginCount(){ return  robotSendLoginCount.incrementAndGet(); }
    public static int incRobotRecvLoginCount(){ return  robotRecvLoginCount.incrementAndGet(); }
    public static int incRobotGetTokenCount(){return robotGetTokenCount.incrementAndGet(); }


    public static int incAppClient(){
        return appClientCount.incrementAndGet();
    }
    public static int incAppHeartBeatCount(){
        return  appHearBeatTickCount.incrementAndGet();
    }
    public static int incAppHandShakeCount(){ return appHandShakeCount.incrementAndGet();}
    public static int incAppSendLoginCount(){ return appSendLoginCount.incrementAndGet(); }
    public static int incAppRecvLoginCount(){ return appRecvLoginCount.incrementAndGet(); }
    public static int incAppGetTokenCount(){return appGetTokenCount.incrementAndGet(); }


    public static int decRobot(){ return robotClientCount.decrementAndGet();}
    public static int decApp(){
        return appClientCount.decrementAndGet();
    }

    public static int getRobotCount() {
        return robotClientCount.get();
    }
    public static int getAppCount() {
        return appClientCount.get();
    }


    public static void print() {
        final StringBuilder sb = new StringBuilder("======================>>>>>StatisticHolder{\n");

        sb.append("robotClientCount='").append(robotClientCount.get()).append('\n');
        sb.append("robotHearBeatTickCount='").append(robotHearBeatTickCount.get()).append('\n');
        sb.append("robotHandShakeCount='").append(robotHandShakeCount.get()).append('\n');
        sb.append("robotSendLoginCount='").append(robotSendLoginCount.get()).append('\n');
        sb.append("robotRecvLoginCount='").append(robotRecvLoginCount.get()).append('\n');
        sb.append("appGetTokenCount='").append(appGetTokenCount.get()).append("\n\n");

        sb.append("appClientCount='").append(appClientCount.get()).append('\n');
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
