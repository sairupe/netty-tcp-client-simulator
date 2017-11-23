package app.client.net.protocol.request;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import com.gowild.protocol.SdkMsgType;
import com.gowild.sdktcp.metadata.pb.MiscAC2SMsgProto;

import java.util.Arrays;


@Protocol(moduleId = SdkMsgType.APP_CLIENT_TYPE, sequenceId = 101, type = ProtocolType.REQUSET)
public class C_APP_LOGIN extends RequestProtocol{
//    //App登陆协议
//    message LoginForAppCMsg {
//        required string token = 1; //token
//    }

    private String token;

    @Override
    public void writeBinaryData(){
        MiscAC2SMsgProto.LoginForAppCMsg.Builder login = MiscAC2SMsgProto.LoginForAppCMsg.newBuilder();
        login.setToken(token);
        byte[] bytes = login.build().toByteArray();
        for(byte b : bytes){
            writeByte(b);
        }
        System.out.println("!!!! - :" + Arrays.toString(bytes));
    }

    public void setToken(String token) {
        this.token = token;
    }
}
