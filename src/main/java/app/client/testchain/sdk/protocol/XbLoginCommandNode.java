package app.client.testchain.sdk.protocol;

import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.C_DEVICE_LOGIN;
import app.client.net.protocol.request.C_XB_LOGIN;
import app.client.testchain.ProtocolListenNode;

/**
 * Created by zh on 2017/11/21.
 */
public class XbLoginCommandNode extends ProtocolListenNode {
    @Override
    public void doExecute() {
        C_XB_LOGIN login = ProtocolFactory.createRequestProtocol(C_XB_LOGIN.class,
                userSession.getCtx());
        login.setMac("94:a1:a2:f3:ec:51");
        userSession.sendMsg(login);
    }

    @Override
    public boolean canExecuteImmediately(){
        return true;
    }
}
