package app.client.service.user;

import app.client.net.protocol.ResponseProtocol;
import app.client.net.protocol.response.S_APP_HEART_BEAT;
import app.client.net.protocol.response.S_DEVICE_HEART_BEAT;
import app.client.net.protocol.response.S_DEVICE_LOGIN_RESULT;
import app.client.net.protocol.response.S_XB_HEART_BEAT;
import app.client.service.IService;
import app.client.user.session.UserSession;

/**
 * 
 * @author syriana.zh
 *
 * 2016年4月21日 下午3:21:18
 */
public interface IUserService extends IService{
	
    public void userLogin(UserSession userSession);
	
    public void receivedUserLoginResponse(S_DEVICE_LOGIN_RESULT response);

    public void receivedHeartBeatResponse(S_DEVICE_HEART_BEAT response);

    public void receivedAppHeartBeatResponse(S_APP_HEART_BEAT response);

    public void receivedXbHeartBeatResponse(S_XB_HEART_BEAT response);
	
    public void createUser(UserSession userSession);
	
	public void receivedCreateUserResponse(ResponseProtocol response);
	
	public void selectUser(long userId);
	
	public void receivedSelectUser(ResponseProtocol response);

	public void xbLogin(UserSession userSession);

    public void appLogin(UserSession userSession);
}
