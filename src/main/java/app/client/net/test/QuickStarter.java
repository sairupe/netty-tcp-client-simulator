package app.client.net.test;

import com.gowild.core.util.LogUtil;

/**
 * Created by zh on 2017/10/27.
 */
public class QuickStarter {

    public static void main(String[] args) throws Exception {
        if(false){
            Thread appStarter = new Thread(new AppStartTask());
            appStarter.start();
            LogUtil.debug("启动APP");
        }

        if(true){
            Thread xbStarter = new Thread(new XbStartTask());
            xbStarter.start();
            LogUtil.debug("启动XB完毕");
        }

        if(false){
            LogUtil.debug("暂停...");
            //Thread.sleep(3000);

            LogUtil.debug("启动 SDK ...");
            Thread sdkStarter = new Thread(new SdkStartTask());
            sdkStarter.start();
        }
    }

    static class AppStartTask implements Runnable{

        @Override
        public void run() {
            Netty4AppClient appClient = new Netty4AppClient();
            try {
                appClient.init();
                appClient.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
            appClient.close();
        }
    }

    static class XbStartTask implements Runnable{

        @Override
        public void run() {
            Netty4XbClient xbClient = new Netty4XbClient();
            try {
                xbClient.init();
                xbClient.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
            xbClient.close();
        }
    }

    static class SdkStartTask implements Runnable{

        @Override
        public void run() {
            Netty4SdkClient sdkClient = new Netty4SdkClient();
            try {
                sdkClient.init();
                sdkClient.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
            sdkClient.close();
        }
    }
}
