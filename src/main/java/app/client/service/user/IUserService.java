package app.client.service.user;

import app.client.net.protocol.response.S_APP_HEART_BEAT;
import app.client.net.protocol.response.S_APP_LOGIN;
import app.client.net.protocol.response.S_XB_CHAT;
import app.client.net.protocol.response.S_XB_HEART_BEAT;
import app.client.net.protocol.response.S_XB_LOGIN;

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

    public void receivedXbLoginRes(S_XB_LOGIN response);

    public void receivedAppLoginRes(S_APP_LOGIN response);
}
