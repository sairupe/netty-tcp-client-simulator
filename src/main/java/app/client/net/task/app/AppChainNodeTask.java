package app.client.net.task.app;

import app.client.testchain.AppChainNodeManager;
import app.client.user.session.UserSession;

/**
 * Created by zh on 2018/5/9.
 */
public class AppChainNodeTask implements Runnable{

    private UserSession userSession;

    @Override
    public void run() {
        AppChainNodeManager.getInstance().start(userSession);
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }
}
