package app.client.utils;

/**
 * Created by zh on 2018/2/7.
 */
public class CommonUtil {

    public static void threadPause(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
