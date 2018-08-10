package app.client.testchain.sdk.protocol.xb;

import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.C_XB_LOGIN;
import app.client.testchain.ProtocolListenNode;

/**
 * Created by zh on 2017/11/21.
 */
public class XbLoginCommandNode extends ProtocolListenNode {
    @Override
    public void doExecute() {
        C_XB_LOGIN xbLogin = ProtocolFactory.createRequestProtocol(C_XB_LOGIN.class,
                userSession.getCtx());
        //2967
//        xbLogin.setMac("94:a1:a2:c0:47:c8");
        //2968
        xbLogin.setMac("94:a1:a2:bf:b7:f0");
        xbLogin.setSn("");

        xbLogin.setVersion("2.1.2");
        xbLogin.setVersionV2("100");

        xbLogin.setbVersion("119");
        xbLogin.setGifV1("157");
        xbLogin.setLoginIp("116.24.65.206");


        userSession.sendMsg(xbLogin);
    }

    @Override
    public boolean canExecuteImmediately(){
        return true;
    }
}
