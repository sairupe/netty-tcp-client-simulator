package app.client.net.test;

import app.client.net.dispacher.DispacherManager;
import app.client.net.task.TaskManager;
import com.gowild.core.util.LogUtil;

/**
 * Created by zh on 2017/10/27.
 */
public class QuickStarter {

    public static void main(String[] args) throws Exception {

        // 初始化协议原型加载
        Class.forName("app.client.net.protocol.ProtocolFactory");
        // 初始化协议加载
        Class.forName("app.client.net.dispacher.DispacherManager");
        // 初始化分发器
        DispacherManager.getInstance().init();
        // 初始化线程池
        TaskManager.getInstance().init();

        if(true){
            Thread appStarter = new Thread(new AppStartTask());
            appStarter.start();
            LogUtil.debug("启动APP");
        }

        if(false){
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
                appClient.close();
            }
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
                xbClient.close();
            }
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
                sdkClient.close();
            }
        }
    }
}
