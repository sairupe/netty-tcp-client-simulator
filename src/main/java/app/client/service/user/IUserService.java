package app.client.service.user;

import app.client.net.protocol.response.S_APP_HEART_BEAT;
import app.client.net.protocol.response.S_APP_LOGIN;

/**
 * 
 * @author syriana.zh
 *
 * 2016年4月21日 下午3:21:18
 */
public interface IUserService{
	
    void receivedAppHeartBeatResponse(S_APP_HEART_BEAT response);

    void receivedAppLoginRes(S_APP_LOGIN response);

}
