package app.client.net.protocol.request;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import com.gowild.core.util.LogUtil;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.xbtcp.metadata.pb.AccountBothMsgProto;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


@Protocol(moduleId = SdkMsgType.XB_CLIENT_TYPE, sequenceId =  151, type = ProtocolType.REQUSET)
public class C_DOCKER_LOGIN extends RequestProtocol{

    private String mac;

    private String url;

    private String token;

    @Override
    public void writeBinaryData(){

//        String token = "";
//        try {
//            HttpClient httpClient = new DefaultHttpClient();
//            HttpPost request = new HttpPost(url);
//            request.setHeader("Authorization", "Basic Z293aWxkX2FwcF9jbGllbnQ6U1VrZSQjODNqZGlzbClESl8uLDMyNA==");
//
//            HttpParams grantType = new BasicHttpParams();
//            grantType.setParameter("grant_type", "password");
//            request.setParams(grantType);
//
//            HttpParams userName = new BasicHttpParams();
//            grantType.setParameter("username", mac);
//            request.setParams(userName);
//
//            HttpParams password = new BasicHttpParams();
//            grantType.setParameter("password", mac);
//            request.setParams(password);
//
//            HttpResponse response = httpClient.execute(request);
//            token = EntityUtils.toString(response.getEntity(), "utf8");
//        } catch (IOException e) {
//            e.printStackTrace();
//            LogUtil.error(e);
//        }


        AccountBothMsgProto.AuthInfo.Builder builder = AccountBothMsgProto.AuthInfo.newBuilder();
        builder.setToken(token);
        byte[] array = builder.build().toByteArray();
        for(byte b : array){
            writeByte(b);
        }
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
