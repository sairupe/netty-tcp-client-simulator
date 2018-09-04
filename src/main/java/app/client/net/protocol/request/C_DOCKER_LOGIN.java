package app.client.net.protocol.request;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import app.client.net.test.Netty4XbClient;
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


    @Override
    public void writeBinaryData(){

        String token = "";
        try {
            String tokenUrl = Netty4XbClient.TOKEN_URL;
            CloseableHttpClient httpclient = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost(tokenUrl);

            List<NameValuePair> list = new ArrayList<NameValuePair>();
            NameValuePair macParam = new BasicNameValuePair("mac", mac);
            NameValuePair snParam = new BasicNameValuePair("serial_no", mac);
            NameValuePair clientIdParam = new BasicNameValuePair("client_id", "e3383afbf2d444cdba823c1a8f25ec12");
            list.add(macParam);
            list.add(snParam);
            list.add(clientIdParam);

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
