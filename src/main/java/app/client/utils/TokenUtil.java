package app.client.utils;

import app.client.data.AppDataHolder;
import app.client.data.RobotDataHolder;
import app.client.net.task.TaskManager;
import app.client.net.task.misc.HttpAppGetTokenTask;
import app.client.net.test.Netty4AppClient;
import app.client.net.test.QuickStarter;
import app.client.vo.RobotVo;
import app.client.vo.UserVo;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zh on 2018/9/6.
 */
@Slf4j
public class TokenUtil {

    private static final Logger logger = LoggerFactory.getLogger(TokenUtil.class);

    private static CloseableHttpClient httpclient = HttpClientBuilder.create().build();

    public static String getRobotToken(String mac) {
        String token = "";
        try {
            String tokenUrl = "";
            HttpPost request = new HttpPost(tokenUrl);

            List<NameValuePair> list = new ArrayList<NameValuePair>();
            NameValuePair macParam = new BasicNameValuePair("mac", mac);
            NameValuePair snParam = new BasicNameValuePair("serial_no", mac);
            NameValuePair clientIdParam = new BasicNameValuePair("client_id", "e3383afbf2d444cdba823c1a8f25ec12");
            list.add(macParam);
            list.add(snParam);
            list.add(clientIdParam);

            CloseableHttpResponse response = null;
            request.setEntity(new UrlEncodedFormEntity(list));
//            long start = System.currentTimeMillis();
            response = HttpClientBuilder.create().build().execute(request);
//            logger.info("=====>>> time " + (System.currentTimeMillis() - start));
            token = EntityUtils.toString(response.getEntity(), "utf8");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("error: {}", e);
        }
        JSONObject result = JSONObject.parseObject(token);
        int code = result.getInteger("code");
        if (10100100 == code) {
            JSONObject data = result.getJSONObject("data");
            token = data.getString("access_token");
            return token;
        }
        return null;
    }

    public static String getAppTokenByCaptcha(String account) {
        String token = "";
        try {
            String tokenUrl = Netty4AppClient.TOKEN_URL;
            HttpPost request = new HttpPost(tokenUrl);
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            NameValuePair macParam = new BasicNameValuePair("mobile", account);
            NameValuePair snParam = new BasicNameValuePair("captcha", "8888");
            NameValuePair clientIdParam = new BasicNameValuePair("client_id", "4403d3900fe74a3cb3df660d249ead0f");
            list.add(macParam);
            list.add(snParam);
            list.add(clientIdParam);

            CloseableHttpResponse response = null;
            request.setEntity(new UrlEncodedFormEntity(list));
//            long start = System.currentTimeMillis();
            response = HttpClientBuilder.create().build().execute(request);
//            logger.info("=====>>> time " + (System.currentTimeMillis() - start));
            token = EntityUtils.toString(response.getEntity(), "utf8");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("error: {}", e);
        }
        JSONObject result = JSONObject.parseObject(token);
        int code = result.getInteger("code");
        if (10100100 == code) {
            JSONObject data = result.getJSONObject("data");
            token = data.getString("access_token");
            return token;
        }
        return null;
    }

    public static String getAppTokenByPwd(String account) {
        String token = "";
        try {
            String tokenUrl = Netty4AppClient.TOKEN_URL;
            HttpPost request = new HttpPost(tokenUrl);
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            NameValuePair grantTypeParam = new BasicNameValuePair("grant_type", "password");
            NameValuePair macParam = new BasicNameValuePair("username", account);
            NameValuePair snParam = new BasicNameValuePair("password", "1234567a");
            NameValuePair clientIdParam = new BasicNameValuePair("client_id", "4403d3900fe74a3cb3df660d249ead0f");
            list.add(grantTypeParam);
            list.add(macParam);
            list.add(snParam);
            list.add(clientIdParam);

            CloseableHttpResponse response = null;
            request.setEntity(new UrlEncodedFormEntity(list));
//            long start = System.currentTimeMillis();
            response = HttpClientBuilder.create().build().execute(request);
//            logger.info("=====>>> time " + (System.currentTimeMillis() - start));
            token = EntityUtils.toString(response.getEntity(), "utf8");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("error: {}", e);
        }
        JSONObject result = JSONObject.parseObject(token);
        int code = result.getInteger("code");
        if (10100100 == code) {
            JSONObject data = result.getJSONObject("data");
            token = data.getString("access_token");
            return token;
        }
        return null;
    }

    public static String otherHttpTest() {
        try {
            String tokenUrl = "";
            HttpPost request = new HttpPost(tokenUrl);

            List<NameValuePair> list = new ArrayList<NameValuePair>();
            NameValuePair macParam = new BasicNameValuePair("accountId", "71");
            NameValuePair snParam = new BasicNameValuePair("brandId", "5");
            NameValuePair clientIdParam = new BasicNameValuePair("types", "DEVICE,MASTER");
            list.add(macParam);
            list.add(snParam);
            list.add(clientIdParam);

            CloseableHttpResponse response = null;
            request.setEntity(new UrlEncodedFormEntity(list));
            long start = System.currentTimeMillis();
            response = httpclient.execute(request);
            logger.info("=====>>> time " + (System.currentTimeMillis() - start));
            String result = EntityUtils.toString(response.getEntity(), "utf8");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("error: {}", e);
        }
        return null;
    }

    public static void initialAllAppToken(boolean initAppToken) throws InterruptedException {
        if(QuickStarter.PRESS_TEST && initAppToken){
            long tokenStart = System.currentTimeMillis();
            Map<Integer, UserVo> id2UserVoMap = AppDataHolder.getId2UserVoMap();
            for (Map.Entry<Integer, UserVo> entry : id2UserVoMap.entrySet()) {
                HttpAppGetTokenTask appGetTokenTask = new HttpAppGetTokenTask(entry.getValue());
                TaskManager.getInstance().addTokenTask(appGetTokenTask);
            }
            logger.info("=====>>>>>>初始化CLIENT TOKEN使用了: " + (System.currentTimeMillis() - tokenStart) + " ms");
        }
    }
}
