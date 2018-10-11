package app.client.net.test;

import app.client.common.Const;
import app.client.data.AppDataHolder;
import app.client.data.RobotDataHolder;
import app.client.data.TokenDataHolder;
import app.client.net.dispacher.DispacherManager;
import app.client.net.task.TaskManager;
import app.client.utils.TokenUtil;
import app.client.vo.RobotVo;
import app.client.vo.UserVo;
import com.gowild.core.util.LogUtil;
import com.gowild.core.util.StringUtil;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Future;

/**
 * Created by zh on 2017/10/27.
 */
public class QuickStarter {

    public static boolean PRESS_TEST = false;
    private static final String tips = "参数格式不对，请输入【robot ID范围(闭区间)】【app ID范围（闭区间）】" +
            "【是否启动ROBOT】【是否更新ROBOT TOKEN】【是否启动APP】【是否更新APP TOKEN】,如1-3000 1-3000 true false true false";

    private static final String ROBOT_ERROR = "机器 ID范围不合法，确保 robotStart > robotEnd || robotEnd < robotStart";

    private static final String APP_ERROR = "APP ID范围不合法，确保 appStart > appEnd || appEnd < appStart";
    private static Logger logger = LoggerFactory.getLogger(QuickStarter.class);
    private static int ROBOT_START = 0;
    private static int ROBOT_END = 0;
    private static int APP_START = 0;
    private static int APP_END = 0;

    public static boolean START_ROBOT;
    public static boolean INIT_ROBOT_TOKEN;

    public static boolean START_APP;
    public static boolean INIT_APP_TOKEN;

    public static void main(String[] args) throws Exception {

//        if(args == null || args.length < 6){
//            logger.error(tips);
//            return;
//        }

//        String robotIdRange = args[0];
//        String appIdRange = args[1];
//        boolean startRobot = Boolean.parseBoolean(args[2]);
//        boolean initRobotToken = Boolean.parseBoolean(args[3]);
//        boolean startApp = Boolean.parseBoolean(args[4]);
//        boolean initAppToken = Boolean.parseBoolean(args[5]);
//
//        String[] robotRange = robotIdRange.split("-");
//        String[] appRange = appIdRange.split("-");
//        robotStart = Integer.parseInt(robotRange[0]);
//        robotEnd = Integer.parseInt(robotRange[1]);
//        appStart = Integer.parseInt(appRange[0]);
//        appEnd = Integer.parseInt(appRange[1]);
//        int robotCount = robotEnd - robotStart + 1;
//        int appCount = appEnd - appStart + 1;

        // 加载配置文件
        Properties prop = new Properties();
        File configFile = new File("config.properties");
        // 如果没有，读取工程中的默认文件
        if (!configFile.exists()) {
            logger.info("====>>> jar包目录下未找到config.properties, 读取工程中的默认文件(实际并没什么用，开发环境DEBUG用)");
            InputStream is = QuickStarter.class.getClassLoader().getResourceAsStream("config.properties");
            prop.load(is);
            is.close();
        } else {
            FileInputStream fis = new FileInputStream(configFile);
            InputStream is = new BufferedInputStream(fis);
            prop.load(is);
            fis.close();
        }

        PRESS_TEST = Boolean.parseBoolean(prop.getProperty("pressTest"));
        int RECONNECT_PERIOD = Integer.parseInt(prop.getProperty("reconnectPeriod"));
        Const.RECONNECT_PERIOD = RECONNECT_PERIOD;
        START_ROBOT = Boolean.parseBoolean(prop.getProperty("startRobot"));
        INIT_ROBOT_TOKEN = Boolean.parseBoolean(prop.getProperty("initRobotToken"));
        START_APP = Boolean.parseBoolean(prop.getProperty("startApp"));
        INIT_APP_TOKEN = Boolean.parseBoolean(prop.getProperty("initAppToken"));

        ROBOT_START = Integer.parseInt(prop.getProperty("robotStart"));
        ROBOT_END = Integer.parseInt(prop.getProperty("robotEnd"));
        APP_START = Integer.parseInt(prop.getProperty("appStart"));
        APP_END = Integer.parseInt(prop.getProperty("appEnd"));

        String ROBOT_TCP_IP = prop.getProperty("robotTcpIp");
        int ROBOT_PORT = Integer.parseInt(prop.getProperty("robotTcpPort"));
        String ROBOT_TOKEN_URL = prop.getProperty("robotTokenUrl");
        Netty4XbClient.HOST = ROBOT_TCP_IP;
        Netty4XbClient.PORT = ROBOT_PORT;
        Netty4XbClient.TOKEN_URL = ROBOT_TOKEN_URL;
        if(START_ROBOT && StringUtils.isEmpty(Netty4XbClient.HOST)
                || StringUtils.isEmpty(Netty4XbClient.TOKEN_URL)
                || Netty4XbClient.PORT == 0){
            logger.info("====>>>>请检查机器的TCP连接和TOKEN配置，配置为空");
            return;
        }

        String APP_TCP_IP = prop.getProperty("appTcpIp");
        int APP_PORT = Integer.parseInt(prop.getProperty("appTcpPort"));
        String APP_TOKEN_URL = prop.getProperty("appTokenUrl");
        Netty4AppClient.HOST = APP_TCP_IP;
        Netty4AppClient.PORT = APP_PORT;
        Netty4AppClient.TOKEN_URL = APP_TOKEN_URL;

        int robotCount = ROBOT_END - ROBOT_START + 1;
        int appCount = APP_END - APP_START + 1;

        StringBuilder configInfo = new StringBuilder();
        configInfo.append("==============启动配置信息=====================>>> \n\n");

        configInfo.append("====>>> !!!!!压测总开关!!!! : " + PRESS_TEST + "\n\n");
        configInfo.append("====>>> !!!!!断线重连间隔!!!! : " + Const.RECONNECT_PERIOD + " ms\n\n");

        configInfo.append("====>>> 是否启动机器端压测 : " + START_ROBOT + "\n");
        configInfo.append("====>>> 是否启动APP端压测 : " + START_APP +"\n");
        configInfo.append("====>>> 是否初始化机器 TOKEN : " + INIT_ROBOT_TOKEN + "\n");
        configInfo.append("====>>> 是否初始化APP TOKEN : " + INIT_APP_TOKEN + "\n");
        configInfo.append("====>>> 压测机器ID范围 : " + ROBOT_START + " - " + ROBOT_END + "\n");
        configInfo.append("====>>> 压测机器数量 :  " + robotCount + "\n");
        configInfo.append("====>>> 压测APP ID范围 : " + APP_START + " - " + APP_END + "\n");
        configInfo.append("====>>> 压测APP数量 :  " + appCount + "\n\n");

        configInfo.append("====>>> 机器TCP链接IP :  " + Netty4XbClient.HOST + "\n");
        configInfo.append("====>>> 机器TCP链接端口 :  " + Netty4XbClient.PORT + "\n");
        configInfo.append("====>>> 机器TOKEN URL地址 :  " + Netty4XbClient.TOKEN_URL + "\n\n");

        configInfo.append("====>>> APP TCP链接IP :  " + Netty4AppClient.HOST + "\n");
        configInfo.append("====>>> APP TCP链接端口 :  " + Netty4AppClient.PORT + "\n");
        configInfo.append("====>>> APP TOKEN URL地址 :  " + Netty4AppClient.TOKEN_URL + "\n");
        configInfo.append("===================================>>> \n\n");

        logger.info(configInfo.toString());

        // 一系列检查
        if (ROBOT_START > ROBOT_END || ROBOT_END < ROBOT_START) {
            logger.info(ROBOT_ERROR);
            return;
        }
        if (APP_START > APP_END || APP_END < APP_START) {
            logger.info(APP_ERROR);
            return;
        }
        if(START_ROBOT && StringUtils.isEmpty(Netty4XbClient.HOST)
                || StringUtils.isEmpty(Netty4XbClient.TOKEN_URL)
                || Netty4XbClient.PORT == 0){
            logger.info("====>>>>请检查机器的TCP连接和TOKEN配置，配置为空");
        }
        if(START_APP && StringUtils.isEmpty(Netty4AppClient.HOST)
                || StringUtils.isEmpty(Netty4AppClient.TOKEN_URL)
                || Netty4AppClient.PORT == 0){
            logger.info("====>>>>请检查APP的TCP连接和TOKEN配置，配置为空");
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
        TokenUtil.initialAllRobotToken(INIT_ROBOT_TOKEN);
        // 初始化 APP TOKEN
        TokenUtil.initialAllAppToken(INIT_APP_TOKEN);
        // 加载所有机器的Token
        TokenDataHolder.loadAllRobotToken();

        if (START_APP) {
            LogUtil.debug("启动APP 压测");
            Thread appStarter;
            if (PRESS_TEST) {
                appStarter = new Thread(new AppPressTestStartTask());
            } else {
                appStarter = new Thread(new AppNormalStartTask());
            }
            appStarter.start();
        }

        if (START_ROBOT) {
            LogUtil.debug("启动ROBOT 压测");
            Thread xbStarter;
            if (PRESS_TEST) {
                xbStarter = new Thread(new XbPressTestStartTask());
            } else {
                xbStarter = new Thread(new XbNormalStartTask());
            }
            xbStarter.start();
        }

        if (false) {
            LogUtil.debug("暂停...");
            LogUtil.debug("启动 SDK ...");
            Thread sdkStarter = new Thread(new SdkStartTask());
            sdkStarter.start();
        }
        logger.info("==========>>>>>>>>>>>> MAIN 函数结束,如果未打印任何信息请结束进程并检查配置 ===========");
    }

    public static void quickStartRobot(String mac, int robotId) {
        Netty4XbClient xbClient = new Netty4XbClient();
        Future<?> future = TaskManager.getInstance().addCreateRobotTask(() -> {
            try {
                xbClient.setMac(mac);
                String token = TokenDataHolder.getIdentifyToken(mac);
                xbClient.setToken(token);
                xbClient.setRobotId(robotId);
                xbClient.init();
                xbClient.start();
            } catch (Exception e) {
                e.printStackTrace();
                xbClient.close();
            }
        });
        xbClient.setLoginFuture(future);
    }

    public static void quickStartApp(String userName, int accountId) {
        Netty4AppClient appClient = new Netty4AppClient();
        Future<?> future = TaskManager.getInstance().addCreateRobotTask(() -> {
            appClient.setAccount(userName);
            String token = TokenDataHolder.getIdentifyToken(userName);
            appClient.setToken(token);
            appClient.setAccountId(accountId);
            try {
                appClient.init();
                appClient.start();
            } catch (Exception e) {
                e.printStackTrace();
                appClient.close();
            }
        });
        appClient.setLoginFuture(future);
    }

    static class AppPressTestStartTask implements Runnable {
        @Override
        public void run() {
            Map<Integer, UserVo> id2UserVoMap = AppDataHolder.getId2UserVoMap();
            for (Map.Entry<Integer, UserVo> entry : id2UserVoMap.entrySet()) {

                if (entry.getKey() < APP_START || entry.getKey() > APP_END) {
                    continue;
                }

                UserVo userVo = entry.getValue();
                quickStartApp(userVo.getUserName(), userVo.getAccountId());
            }
        }
    }

    static class XbPressTestStartTask implements Runnable {
        @Override
        public void run() {
            Map<Integer, RobotVo> id2RotbotVoMap = RobotDataHolder.getId2RotbotVoMap();
            for (Map.Entry<Integer, RobotVo> entry : id2RotbotVoMap.entrySet()) {

                if (entry.getKey() < ROBOT_START || entry.getKey() > ROBOT_END) {
                    continue;
                }

                RobotVo robotVo = entry.getValue();
                quickStartRobot(robotVo.getMac(), robotVo.getRobotId());
            }
        }
    }

    static class AppNormalStartTask implements Runnable {
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

    static class XbNormalStartTask implements Runnable {
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

    static class SdkStartTask implements Runnable {

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
