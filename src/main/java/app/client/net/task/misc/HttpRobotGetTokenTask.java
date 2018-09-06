package app.client.net.task.misc;

import app.client.data.RobotDataHolder;
import app.client.data.TokenDataHolder;
import app.client.utils.TokenUtil;
import app.client.vo.RobotVo;

/**
 * Created by zh on 2018/9/6.
 */
public class HttpRobotGetTokenTask implements Runnable {

    private RobotVo robotVo;

    public HttpRobotGetTokenTask(RobotVo robotVo){
        this.robotVo = robotVo;
    }

    @Override
    public void run() {
        String mac = robotVo.getMac();
        String robotToken = TokenUtil.getRobotToken(mac);
        robotVo.setToken(robotToken);
        TokenDataHolder.updateToken(mac, robotToken);
        RobotDataHolder.getRobotLatch().countDown();
    }
}
