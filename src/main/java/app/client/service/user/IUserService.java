package app.client.service.user;

import app.client.net.protocol.response.S_APP_HEART_BEAT;
import app.client.net.protocol.response.S_APP_LOGIN;
import app.client.net.protocol.response.S_XB_CHAT;
import app.client.net.protocol.response.S_XB_CHAT_V2;
import app.client.net.protocol.response.S_XB_HEART_BEAT;
import app.client.net.protocol.response.S_XB_LOGIN;
import app.client.net.protocol.response.S_XB_REV_KEDA;
import app.client.net.protocol.response.S_XB_SEMANTIC_FINISH;

/**
 * 
 * @author syriana.zh
 *
 * 2016年4月21日 下午3:21:18
 */
public interface IUserService{
	
    public void receivedAppHeartBeatResponse(S_APP_HEART_BEAT response);

    public void receivedXbHeartBeatResponse(S_XB_HEART_BEAT response);

    public void receivedXbChatResponse(S_XB_CHAT response);

    public void receivedXbChatV2Response(S_XB_CHAT_V2 response);

    public void receivedXbLoginRes(S_XB_LOGIN response);

    public void receivedAppLoginRes(S_APP_LOGIN response);

    public void receivedKedaRes(S_XB_REV_KEDA response);

    public void receivedSemanticFinishRes(S_XB_SEMANTIC_FINISH response);
}
