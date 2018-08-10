package app.client.testchain.sdk.protocol.docker;

import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.C_DOCKER_LOGIN;
import app.client.testchain.ProtocolListenNode;

/**
 * Created by zh on 2017/11/21.
 */
public class Docker2LoginCommandNode extends ProtocolListenNode {
    @Override
    public void doExecute() {
        C_DOCKER_LOGIN dockerLogin = ProtocolFactory.createRequestProtocol(C_DOCKER_LOGIN.class,
                userSession.getCtx());

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiZ293aWxkIl0sInVzZXJfbmFtZSI6Ijk0OmExOmEyOmJmOmI3OmYwIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIiwidHJ1c3QiXSwiZXhwIjoxNTM2MzkxNTE0LCJhdXRob3JpdGllcyI6WyJST0xFX0FQUCJdLCJqdGkiOiI0NGJlYzI4ZC04MjU3LTRhMDctYjhkMC04NDUwYTkxNmQxYzYiLCJjbGllbnRfaWQiOiJnb3dpbGRfYXBwX2NsaWVudCJ9.Wk9I4sRw7fsXVVTAO-5-1G9SNUmuCi7uupP1KBDzWZk";

//        dockerLogin.setUrl("http://172.27.1.49:6110/oauth/token");
        //2967
//        dockerLogin.setMac("94:a1:a2:c0:47:c8");
        //2968
        dockerLogin.setMac("94:a1:a2:bf:b7:f0");
        //2969
//        dockerLogin.setMac("94:a1:a2:c0:37:2c");


        dockerLogin.setToken(token);
        userSession.sendMsg(dockerLogin);
    }

    @Override
    public boolean canExecuteImmediately(){
        return true;
    }
}
