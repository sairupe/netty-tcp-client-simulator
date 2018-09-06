package app.client.net.task.misc;

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
        String robotToken = TokenUtil.getRobotToken(robotVo.getMac());
        robotVo.setToken(robotToken);
//        TokenUtil.otherHttpTest();
    }
}
