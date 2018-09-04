package app.client.net.protocol.request;

import app.client.net.annotation.Protocol;
import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.ProtocolType;
import app.client.net.protocol.RequestProtocol;
import app.client.net.task.TaskManager;
import app.client.net.task.app.AppHeartBeatTask;
import app.client.net.test.Netty4AppClient;
import com.gowild.core.util.HttpUtil;
import com.gowild.sdk.protocol.SdkMsgType;
import com.gowild.xbtcp.metadata.pb.MiscAC2SMsgProto;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Protocol(moduleId = SdkMsgType.APP_CLIENT_TYPE, sequenceId = 151, type = ProtocolType.REQUSET)
public class C_APP_LOGIN extends RequestProtocol {

    private String mobile;

    @Override
    public void writeBinaryData() {

        try {
            Map<String, String> header = new HashMap<>();
            Map<String, String> param = new HashMap<>();
            param.put("mobile", mobile);
            param.put("captcha", "8888");
            param.put("client_id", "4403d3900fe74a3cb3df660d249ead0f");
            String repsonse2 = null;
            String url = Netty4AppClient.TOKEN_URL;
            repsonse2 = HttpUtil.sendWebRequestByForm(null, url, header, param, null);
            JSONObject result = new JSONObject(repsonse2);
            JSONObject data = result.getJSONObject("data");
            String loginToken = data.get("access_token").toString();

            MiscAC2SMsgProto.LoginForAppCMsg.Builder login = MiscAC2SMsgProto.LoginForAppCMsg.newBuilder();
            login.setToken(loginToken);
            byte[] bytes = login.build().toByteArray();
            writeBytes(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
