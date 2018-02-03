package app.client.service.device;

import app.client.net.protocol.ResponseProtocol;
import app.client.net.protocol.response.*;
import app.client.service.IService;
import app.client.user.session.UserSession;

/**
 * 
 * @author syriana.zh
 *
 * 2016年4月21日 下午3:21:18
 */
public interface IDeviceService extends IService{
	
    public void sendSimularCmd(UserSession userSession);

//    public void receiveDeviceCmd(S_DEVICE_CMD response);
//
//    public void receiveDeviceTypeCmd(S_DEVICE_TYPE_CMD response);
//
//    public void receiveSceneCmd(S_SCENE_CMD response);
//
//    public void receiveAreaCmd(S_AREA_CMD response);

      public void receiveStateCommand(S_DEVICE_STATE_COMMAND response);
}
