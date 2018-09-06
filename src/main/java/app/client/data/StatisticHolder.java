package app.client.data;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zh on 2018/9/4.
 */
public class StatisticHolder {

    private static AtomicInteger robotClientCount = new AtomicInteger(0);
    private static AtomicInteger robotHearBeatTickCount = new AtomicInteger(0);
    private static AtomicInteger robotHandShakeCount = new AtomicInteger(0);
    private static AtomicInteger robotLoginCount = new AtomicInteger(0);

    private static AtomicInteger appClientCount = new AtomicInteger(0);
    private static AtomicInteger appHearBeatTickCount = new AtomicInteger(0);
    private static AtomicInteger appHandShakeCount = new AtomicInteger(0);
    private static AtomicInteger appLoginCount = new AtomicInteger(0);

    public static int incRobotClient(){ return robotClientCount.incrementAndGet();}
    public static int incRobotHeartBeatCount(){
        return robotHearBeatTickCount.incrementAndGet();
    }
    public static int incRobotHandShakeCount(){ return robotHandShakeCount.incrementAndGet();}
    public static int incRobotLoginCount(){ return  robotLoginCount.incrementAndGet(); }


    public static int incAppClient(){
        return appClientCount.incrementAndGet();
    }
    public static int incAppHeartBeatCount(){
        return  appHearBeatTickCount.incrementAndGet();
    }
    public static int incAppHandShakeCount(){ return appHandShakeCount.incrementAndGet();}
    public static int incAppLoginCount(){ return appLoginCount.incrementAndGet(); }


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
        sb.append("robotLoginCount='").append(robotLoginCount.get()).append('\n');
        sb.append("appClientCount='").append(appClientCount.get()).append('\n');
        sb.append("appHearBeatTickCount='").append(appHearBeatTickCount.get()).append('\n');
        sb.append("appHandShakeCount='").append(appHandShakeCount.get()).append('\n');
        sb.append("appLoginCount='").append(appLoginCount.get()).append('\n');
        sb.append('}');
        System.out.println(sb.toString());
    }

}
