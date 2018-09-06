package app.client.data;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zh on 2018/9/4.
 */
public class StatisticHolder {

    private static AtomicInteger robotClientCount = new AtomicInteger(0);

    private static AtomicInteger appClientCount = new AtomicInteger(0);

    public static int getRobotCount() {
        return robotClientCount.get();
    }

    public static int getAppCount() {
        return appClientCount.get();
    }

    public static int incRobot(){
        return robotClientCount.incrementAndGet();
    }

    public static int incApp(){
        return appClientCount.incrementAndGet();
    }

    public static int decRobot(){
        return robotClientCount.decrementAndGet();
    }

    public static int decApp(){
        return appClientCount.decrementAndGet();
    }
}
