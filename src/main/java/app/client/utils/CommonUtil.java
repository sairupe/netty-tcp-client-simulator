package app.client.utils;

/**
 * Created by zh on 2018/2/7.
 */
public class CommonUtil {

    public static void threadPause(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
