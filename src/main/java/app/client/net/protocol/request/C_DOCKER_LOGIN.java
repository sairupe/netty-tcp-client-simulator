package app.client.net.protocol.request;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import com.gowild.core.util.LogUtil;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.xbtcp.metadata.pb.AccountBothMsgProto;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


@Protocol(moduleId = SdkMsgType.XB_CLIENT_TYPE, sequenceId =  151, type = ProtocolType.REQUSET)
public class C_DOCKER_LOGIN extends RequestProtocol{

    private String mac;

    private String url = "http://172.27.1.49:6110/oauth/token";

    private String token;

    @Override
    public void writeBinaryData(){

        String token = "";
        try {
            CloseableHttpClient httpclient = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost(url);
            request.setHeader("Authorization", "Basic Z293aWxkX2FwcF9jbGllbnQ6U1VrZSQjODNqZGlzbClESl8uLDMyNA==");

            List<NameValuePair> list = new ArrayList<NameValuePair>();
            NameValuePair grant_type = new BasicNameValuePair("grant_type", "password");
            NameValuePair userName = new BasicNameValuePair("username", mac);
            NameValuePair password = new BasicNameValuePair("password", mac);
            list.add(grant_type);
            list.add(userName);
            list.add(password);

            CloseableHttpResponse response = null;
            try {
                request.setEntity(new UrlEncodedFormEntity(list));
                response = httpclient.execute(request);
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            token = EntityUtils.toString(response.getEntity(), "utf8");
            httpclient.close();
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.error(e);
        }

        JSONObject result = new JSONObject(token);
        int code = result.getInt("code");
        if(10100100 == code){
            JSONObject data = result.getJSONObject("data");
            token = data.getString("access_token");
            AccountBothMsgProto.AuthInfo.Builder builder = AccountBothMsgProto.AuthInfo.newBuilder();
            builder.setToken(token);
            byte[] array = builder.build().toByteArray();
            for(byte b : array){
                writeByte(b);
            }
        }
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
