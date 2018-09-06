package app.client.testchain.sdk.protocol.docker;

import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.C_DOCKER_LOGIN;
import app.client.net.protocol.request.C_XB_LOGIN;
import app.client.testchain.ProtocolListenNode;

/**
 * Created by zh on 2017/11/21.
 */
public class DockerLoginCommandNode extends ProtocolListenNode {

    private String token;

    public DockerLoginCommandNode(String token){
        this.token = token;
    }

    @Override
    public void doExecute() {
        C_DOCKER_LOGIN dockerLogin = ProtocolFactory.createRequestProtocol(C_DOCKER_LOGIN.class,
                userSession.getCtx());

//        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiZ293aWxkIl0sInVzZXJfbmFtZSI6Ijk0OmExOmEyOmMwOjM3OjJjIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIiwidHJ1c3QiXSwiZXhwIjoxNTM2NDAxODk5LCJhdXRob3JpdGllcyI6WyJST0xFX0FQUCJdLCJqdGkiOiJjYzA4YjYwYy0wNjU3LTQ4N2UtODU1OS1hZDJmZWFkYjAwZmQiLCJjbGllbnRfaWQiOiJnb3dpbGRfYXBwX2NsaWVudCJ9.hxA_pLMc-UELuBBBSGfBHHPqVj7RlMT1qzDM1z7_tjg";

//        dockerLogin.setUrl("http://172.27.1.49:6110/oauth/token");
        //2967
//        dockerLogin.setMac("94:a1:a2:c0:47:c8");
        //2968
//        dockerLogin.setMac("94:a1:a2:bf:b7:f0");
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
