package app.client.net.task.misc;

import app.client.data.RobotDataHolder;
import app.client.data.StatisticHolder;
import app.client.data.TokenDataHolder;
import app.client.utils.TokenUtil;
import app.client.vo.RobotVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zh on 2018/9/6.
 */
public class HttpRobotGetTokenTask implements Runnable {

    Logger logger = LoggerFactory.getLogger(getClass());

    private RobotVo robotVo;

    public HttpRobotGetTokenTask(RobotVo robotVo){
        this.robotVo = robotVo;
    }

    @Override
    public void run() {
        String mac = robotVo.getMac();
        String robotToken = TokenUtil.getRobotToken(mac);
        StatisticHolder.incRobotGetTokenCount();
        logger.info("====>>>>>获取机器 mac:【{}】的token：【{}】", mac, robotToken);
        TokenDataHolder.updateToken(mac, robotToken);
    }
}
