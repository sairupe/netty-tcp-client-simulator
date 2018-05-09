package app.client.net.task.sdk;

import app.client.testchain.ChainNodeManager;
import app.client.user.session.UserSession;

/**
 * Created by zh on 2018/5/9.
 */
public class SdkChainNodeTask implements Runnable{

    private UserSession userSession;

    @Override
    public void run() {
        ChainNodeManager.getInstance().start(userSession);
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }
}
