package app.client.service.home;

import app.client.net.protocol.response.sdk.device.S_ADD_DEVICE_RESULT;
import app.client.net.protocol.response.sdk.device.S_DELETE_DEVICE_RESULT;
import app.client.net.protocol.response.sdk.device.S_DEVICE_ATTR_COMMAND;
import app.client.net.protocol.response.sdk.device.S_DEVICE_MODE_COMMAND;
import app.client.net.protocol.response.sdk.device.S_DEVICE_STATE_COMMAND;
import app.client.net.protocol.response.sdk.device.S_SYNC_DEVICE_RESULT;
import app.client.net.protocol.response.sdk.home.S_ADD_HOME_RESULT;
import app.client.net.protocol.response.sdk.home.S_DELETE_HOME_RESULT;
import app.client.net.protocol.response.sdk.home.S_SYNC_HOME_RESULT;
import app.client.net.protocol.response.sdk.home.S_UPDATE_HOME_RESULT;
import app.client.service.IService;
import app.client.user.session.UserSession;

/**
 * 
 * @author syriana.zh
 *
 * 2016年4月21日 下午3:21:18
 */
public interface IHomeService extends IService{
	
    public void addHomeResult(S_ADD_HOME_RESULT response);

    public void deleteHomeResult(S_DELETE_HOME_RESULT response);

    public void updateHomeResult(S_UPDATE_HOME_RESULT response);

    public void syncHomeResult(S_SYNC_HOME_RESULT response);
}
