package app.client.service.device;

import app.client.net.protocol.response.sdk.device.S_ADD_DEVICE_RESULT;
import app.client.net.protocol.response.sdk.device.S_DELETE_DEVICE_RESULT;
import app.client.net.protocol.response.sdk.device.S_DEVICE_ATTR_COMMAND;
import app.client.net.protocol.response.sdk.device.S_DEVICE_MODE_COMMAND;
import app.client.net.protocol.response.sdk.device.S_DEVICE_STATE_COMMAND;
import app.client.net.protocol.response.sdk.device.S_GET_ALL_XB_BIND_MASTER;
import app.client.net.protocol.response.sdk.device.S_SYNC_DEVICE_RESULT;
import app.client.net.protocol.response.sdk.device.S_UPDATE_DEVICE_RESULT;
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

    public void receiveAttrCommand(S_DEVICE_ATTR_COMMAND response);

    public void receiveModeCommand(S_DEVICE_MODE_COMMAND response);

    public void receiveAddMasterBindDeviceResult(S_ADD_DEVICE_RESULT response);

    public void receiveDeleteMasterBindDeviceResult(S_DELETE_DEVICE_RESULT response);

    public void receiveSyncMasterBindDeviceResult(S_SYNC_DEVICE_RESULT response);

    public void receiveUpdateMasterBindDeviceResult(S_UPDATE_DEVICE_RESULT response);

    public void receiveGetXbBindAllMasterInfoResult(S_GET_ALL_XB_BIND_MASTER response);

}
