package app.client.net.test;

import app.client.data.AppDataHolder;
import app.client.data.RobotDataHolder;
import app.client.data.StatisticHolder;
import app.client.data.TokenDataHolder;
import app.client.net.dispacher.DispacherManager;
import app.client.net.task.TaskManager;
import app.client.utils.TokenUtil;
import app.client.vo.RobotVo;
import app.client.vo.UserVo;
import com.gowild.core.util.LogUtil;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

/**
 * Created by zh on 2017/10/27.
 */
public class QuickStarter {

    private static Logger logger = LoggerFactory.getLogger(QuickStarter.class);
    private static final String tips = "参数格式不对，请输入【robot ID范围(闭区间)】【app ID范围（闭区间）】" +
            "【是否启动ROBOT】【是否更新ROBOT TOKEN】【是否启动APP】【是否更新APP TOKEN】,如1-3000 1-3000 true false true false";

    public static final boolean PRESS_TEST = true;

    private static int robotStart = 0;
    private static int robotEnd = 0;
    private static int appStart = 0;
    private static int appEnd = 0;

    public static void main(String[] args) throws Exception {

        if(args == null || args.length < 6){
            logger.error(tips);
            return;
        }
        String robotIdRange = args[0];
        String appIdRange = args[1];
        boolean startRobot = Boolean.parseBoolean(args[2]);
        boolean initRobotToken = Boolean.parseBoolean(args[3]);
        boolean startApp = Boolean.parseBoolean(args[4]);
        boolean initAppToken = Boolean.parseBoolean(args[5]);

        String[] robotRange = robotIdRange.split("-");
        String[] appRange = appIdRange.split("-");
        robotStart = Integer.parseInt(robotRange[0]);
        robotEnd = Integer.parseInt(robotRange[1]);
        appStart = Integer.parseInt(appRange[0]);
        appEnd = Integer.parseInt(appRange[1]);
        int robotCount = robotEnd - robotStart + 1;
        int appCount = appEnd - appStart + 1;

        if(robotStart > robotEnd || robotEnd < robotStart){
            logger.info(tips);
            return;
        }

        if(appStart > appEnd || appEnd < appStart){
            logger.info(tips);
            return;
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
        // 设置robot和app的模拟端数量
        RobotDataHolder.setRobotClientCountAndUpdateLatch(robotCount);
        AppDataHolder.setAppClientCountAndUpdateLatch(appCount);
        // 初始化分发器
        DispacherManager.getInstance().init();
        // 初始化线程池
        TaskManager.getInstance().init();

        // 初始化统计任务打印
        TaskManager.getInstance().initStatiscTask();
        // 初始化 机器 TOKEN
        TokenUtil.initialAllRobotToken(initRobotToken);
        // 初始化 APP TOKEN
        TokenUtil.initialAllAppToken(initAppToken);
        // 加载所有机器的Token
        TokenDataHolder.loadAllRobotToken();;

        if(startApp){
            LogUtil.debug("启动APP 压测");
            Thread appStarter;
            if(PRESS_TEST){
                appStarter = new Thread(new AppPressTestStartTask());
            } else{
                appStarter = new Thread(new AppNormalStartTask());
            }
            appStarter.start();
        }

        if(startRobot){
            LogUtil.debug("启动ROBOT 压测");
            Thread xbStarter;
            if(PRESS_TEST){
                xbStarter = new Thread(new XbPressTestStartTask());
            } else{
                xbStarter = new Thread(new XbNormalStartTask());
            }
            xbStarter.start();
        }

        if(false){
            LogUtil.debug("暂停...");
            LogUtil.debug("启动 SDK ...");
            Thread sdkStarter = new Thread(new SdkStartTask());
            sdkStarter.start();
        }

        Scanner input=new Scanner(System.in);
        String str=null;
        do {
            System.out.print("请输入:#结束程序");
            str=input.next();
        } while (!str.equals("#"));
        System.out.println("程序结束!");
    }

    static class AppPressTestStartTask implements Runnable{
        @Override
        public void run() {
//            CountDownLatch latch = new CountDownLatch(1);
            Map<Integer, UserVo> id2UserVoMap = AppDataHolder.getId2UserVoMap();
            for(Map.Entry<Integer, UserVo> entry : id2UserVoMap.entrySet()){

                if(entry.getKey() < appStart || entry.getKey() > appEnd){
                    continue;
                }

//                Thread thread = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Netty4AppClient appClient = new Netty4AppClient();
//                        String userName = entry.getValue().getUserName();
//                        appClient.setAccount(userName);
//                        String token = TokenDataHolder.getIdentifyToken(userName);
//                        appClient.setToken(token);
//                        try {
////                            logger.info("===================>>>>啓動APP CLIENT綫程，目前CLIENT數量為：" + StatisticHolder.getAppCount());
////                            latch.await();
//                            appClient.init();
//                            appClient.start();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            appClient.close();
//                        }
//                    }
//                });
//                thread.start();
                TaskManager.getInstance().addCreateRobotTask(() ->{
                    Netty4AppClient appClient = new Netty4AppClient();
                    String userName = entry.getValue().getUserName();
                    appClient.setAccount(userName);
                    String token = TokenDataHolder.getIdentifyToken(userName);
                    appClient.setToken(token);
                    try {
                        appClient.init();
                        appClient.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                        appClient.close();
                    }
                });
            }
//            latch.countDown();
        }
    }

    static class XbPressTestStartTask implements Runnable{
        @Override
        public void run() {
//            final CountDownLatch latch = new CountDownLatch(1);
            Map<Integer, RobotVo> id2RotbotVoMap = RobotDataHolder.getId2RotbotVoMap();
            for(Map.Entry<Integer, RobotVo> entry : id2RotbotVoMap.entrySet()){

                if(entry.getKey() < robotStart || entry.getKey() > robotEnd){
                    continue;
                }

//                Thread thread = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Netty4XbClient xbClient = new Netty4XbClient();
//                        try {
//                            String mac = entry.getValue().getMac();
//                            xbClient.setMac(mac);
//                            String token = TokenDataHolder.getIdentifyToken(mac);
//                            xbClient.setToken(token);
////                            logger.info("===================>>>>啓動XB CLIENT綫程，目前CLIENT數量為：" + StatisticHolder.getRobotCount());
////                            latch.await();
//                            xbClient.init();
//                            xbClient.start();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            xbClient.close();
//                        }
//                    }
//                });
//                thread.start();
                TaskManager.getInstance().addCreateRobotTask(() -> {
                    Netty4XbClient xbClient = new Netty4XbClient();
                    try {
                        String mac = entry.getValue().getMac();
                        xbClient.setMac(mac);
                        String token = TokenDataHolder.getIdentifyToken(mac);
                        xbClient.setToken(token);
                        xbClient.init();
                        xbClient.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                        xbClient.close();
                    }
                });
            }
//            latch.countDown();
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
