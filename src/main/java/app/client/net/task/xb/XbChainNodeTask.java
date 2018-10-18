package app.client.net.task.xb;

import app.client.testchain.XbChainNodeManager;
import app.client.user.session.UserSession;

/**
 * Created by zh on 2018/5/9.
 */
public class XbChainNodeTask implements Runnable{

    private UserSession userSession;

    @Override
    public void run() {
        XbChainNodeManager xbChainNodeManager = new XbChainNodeManager();
        xbChainNodeManager.start(userSession);
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }
}
