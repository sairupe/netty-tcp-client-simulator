package app.client.net.task.misc;

import app.client.data.AppDataHolder;
import app.client.data.RobotDataHolder;
import app.client.data.StatisticHolder;
import app.client.data.TokenDataHolder;
import app.client.utils.TokenUtil;
import app.client.vo.RobotVo;
import app.client.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zh on 2018/9/6.
 */
public class HttpAppGetTokenTask implements Runnable {

    Logger logger = LoggerFactory.getLogger(getClass());

    private UserVo userVo;

    public HttpAppGetTokenTask(UserVo userVo){
        this.userVo = userVo;
    }

    @Override
    public void run() {
        String userName = userVo.getUserName();
        String userToken = TokenUtil.getAppToken(userName);
        StatisticHolder.incAppGetTokenCount();
        logger.info("====>>>>>获取APP userName:【{}】的token：【{}】", userName, userToken);
        userVo.setToken(userToken);
        TokenDataHolder.updateToken(userName, userToken);
        AppDataHolder.getAppLatch().countDown();
    }
}
