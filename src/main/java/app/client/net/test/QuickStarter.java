package app.client.net.test;

import app.client.data.AppDataHolder;
import app.client.data.RobotDataHolder;
import app.client.data.StatisticHolder;
import app.client.net.dispacher.DispacherManager;
import app.client.net.task.TaskManager;
import app.client.utils.TokenUtil;
import app.client.vo.RobotVo;
import app.client.vo.UserVo;
import com.gowild.core.util.LogUtil;
import jdk.nashorn.internal.parser.Token;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created by zh on 2017/10/27.
 */
public class QuickStarter {

    public static void main(String[] args) throws Exception {

        // 初始化NIO
        Class.forName("app.client.net.socket.EventLoopHolder");
        // 初始化协议原型加载
        Class.forName("app.client.net.protocol.ProtocolFactory");
        // 初始化协议加载
        Class.forName("app.client.net.dispacher.DispacherManager");
        // 初始化DB连接
        Class.forName("app.client.data.DbConnecter");
        // 初始化数据机器、用户数据装载
        Class.forName("app.client.data.AppDataHolder");
        Class.forName("app.client.data.RobotDataHolder");
        // 初始化分发器
        DispacherManager.getInstance().init();
        // 初始化线程池
        TaskManager.getInstance().init();
        // 初始化统计任务打印
        TaskManager.getInstance().initStatiscTask();
        // 初始化所有TOKEN
        long tokenStart = System.currentTimeMillis();
        TokenUtil.initialAllRobotToken();
//        TokenUtil.initialAllAppToken();
        System.out.println("=====>>>>>>初始化TOKEN使用了: " + (System.currentTimeMillis() - tokenStart) + " ms");

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
            int startCount = 0;
            int maxCount = 10000;
            CountDownLatch latch = new CountDownLatch(1);
            Map<Integer, UserVo> id2UserVoMap = AppDataHolder.getId2UserVoMap();
            for(Map.Entry<Integer, UserVo> entry : id2UserVoMap.entrySet()){
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Netty4AppClient appClient = new Netty4AppClient();
                        appClient.setAccount(entry.getValue().getUserName());
                        appClient.setToken(entry.getValue().getToken());
                        try {

                            System.out.println("===================>>>>啓動APP CLIENT綫程，目前CLIENT數量為：" + StatisticHolder.getAppCount());
                            latch.await();
                            appClient.init();
                            appClient.start();
                        } catch (Exception e) {
                            e.printStackTrace();
                            appClient.close();
                        }
                    }
                });
                thread.start();
                latch.countDown();
                if(startCount >= maxCount){
                    break;
                }
            }
        }
    }

    static class XbStartTask implements Runnable{

        @Override
        public void run() {
            int startCount = 0;
            int maxCount = 1000;
            final CountDownLatch latch = new CountDownLatch(1);
            Map<Integer, RobotVo> id2RotbotVoMap = RobotDataHolder.getId2RotbotVoMap();
            for(Map.Entry<Integer, RobotVo> entry : id2RotbotVoMap.entrySet()){
                startCount++;
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Netty4XbClient xbClient = new Netty4XbClient();
                        try {
                            xbClient.setMac(entry.getValue().getMac());
                            xbClient.setToken(entry.getValue().getToken());
                            System.out.println("===================>>>>啓動XB CLIENT綫程，目前CLIENT數量為：" + StatisticHolder.getRobotCount());
                            latch.await();
                            xbClient.init();
                            xbClient.start();
                        } catch (Exception e) {
                            e.printStackTrace();
                            xbClient.close();
                        }
                    }
                });
                thread.start();
                if(startCount >= maxCount){
                    break;
                }
            }
            latch.countDown();
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
