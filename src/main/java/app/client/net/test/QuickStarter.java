package app.client.net.test;

import app.client.data.AppDataHolder;
import app.client.data.RobotDataHolder;
import app.client.data.TokenDataHolder;
import app.client.net.dispacher.DispacherManager;
import app.client.net.task.TaskManager;
import app.client.utils.TokenUtil;
import app.client.vo.RobotVo;
import app.client.vo.UserVo;
import com.gowild.core.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created by zh on 2017/10/27.
 */
public class QuickStarter {

    private static Logger logger = LoggerFactory.getLogger(QuickStarter.class);

    public static final boolean PRESS_TEST = true;
    public static final boolean INIT_TOKEN = false;

    public static void main(String[] args) throws Exception {

        if(true){
            logger.info("开始测试123123");
            logger.info("===>>>>>>>>>>>>> info..");
            logger.error("===>>>>>>>>>>>>> error..");
            logger.debug("===>>>>>>>>>>>>> debug..");
            return ;
        }

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
        // 初始化机器TOKEN
        TokenUtil.initialAllRobotToken();
        // 加载所有机器的Token
        TokenDataHolder.loadAllRobotToken();;

        if(false){
            Thread appStarter;
            if(PRESS_TEST){
                appStarter = new Thread(new AppPressTestStartTask());
            } else{
                appStarter = new Thread(new AppNormalStartTask());
            }
            appStarter.start();
            LogUtil.debug("启动APP");
        }

        if(true){
            Thread xbStarter;
            if(PRESS_TEST){
                xbStarter = new Thread(new XbPressTestStartTask());
            } else{
                xbStarter = new Thread(new XbNormalStartTask());
            }
            xbStarter.start();
            LogUtil.debug("启动XB完毕");
        }

        if(false){
            LogUtil.debug("暂停...");
            LogUtil.debug("启动 SDK ...");
            Thread sdkStarter = new Thread(new SdkStartTask());
            sdkStarter.start();
        }
    }

    static class AppPressTestStartTask implements Runnable{
        @Override
        public void run() {
            int startCount = 0;
            int maxCount = AppDataHolder.appClientCount;
            CountDownLatch latch = new CountDownLatch(1);
            Map<Integer, UserVo> id2UserVoMap = AppDataHolder.getId2UserVoMap();
            for(Map.Entry<Integer, UserVo> entry : id2UserVoMap.entrySet()){
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Netty4AppClient appClient = new Netty4AppClient();
                        String userName = entry.getValue().getUserName();
                        appClient.setAccount(userName);
                        String token = TokenDataHolder.getIdentifyToken(userName);
                        appClient.setToken(token);
                        try {

//                            logger.info("===================>>>>啓動APP CLIENT綫程，目前CLIENT數量為：" + StatisticHolder.getAppCount());
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

    static class XbPressTestStartTask implements Runnable{
        @Override
        public void run() {
            int startCount = 0;
            int maxCount = RobotDataHolder.robotClientCount;
            final CountDownLatch latch = new CountDownLatch(1);
            Map<Integer, RobotVo> id2RotbotVoMap = RobotDataHolder.getId2RotbotVoMap();
            for(Map.Entry<Integer, RobotVo> entry : id2RotbotVoMap.entrySet()){
                startCount++;
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Netty4XbClient xbClient = new Netty4XbClient();
                        try {
                            String mac = entry.getValue().getMac();
                            xbClient.setMac(mac);
                            String token = TokenDataHolder.getIdentifyToken(mac);
                            xbClient.setToken(token);
//                            logger.info("===================>>>>啓動XB CLIENT綫程，目前CLIENT數量為：" + StatisticHolder.getRobotCount());
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

    static class AppNormalStartTask implements Runnable{
        @Override
        public void run() {
            Netty4AppClient appClient = new Netty4AppClient();
            try {
                appClient.init();
                appClient.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class XbNormalStartTask implements Runnable{
        @Override
        public void run() {
            Netty4XbClient xbClient = new Netty4XbClient();
            try {
                xbClient.init();
                xbClient.start();
            } catch (Exception e) {
                e.printStackTrace();
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
