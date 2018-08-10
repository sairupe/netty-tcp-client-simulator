package app.client.service.device;

import app.client.net.protocol.response.sdk.S_DOCKER_SPEAK;
import app.client.net.protocol.response.sdk.batch.device.S_ADD_DEVICE_BATCH;
import app.client.net.protocol.response.sdk.batch.device.S_DELETE_DEVICE;
import app.client.net.protocol.response.sdk.batch.device.S_DEVICE_ATTR_COMMAND;
import app.client.net.protocol.response.sdk.batch.device.S_DEVICE_MODE_COMMAND;
import app.client.net.protocol.response.sdk.batch.device.S_DEVICE_STATE_COMMAND;
import app.client.net.protocol.response.sdk.batch.device.S_SCENE_COMMAND;
import app.client.net.protocol.response.sdk.batch.device.S_SYNC_DEVICE;
import app.client.net.protocol.response.sdk.batch.device.S_UPDATE_DEVICE_BATCH;
import app.client.net.protocol.response.sdk.batch.device.S_UPDATE_DEVICE_BIND_AREA_BATCH;
import app.client.net.protocol.response.sdk.batch.device.S_UPDATE_DEVICE_BIND_SCENE_BATCH;
import app.client.net.protocol.response.sdk.single.device.S_ADD_DEVICE;
import app.client.net.protocol.response.sdk.single.device.S_UPDATE_DEVICE;
import app.client.net.protocol.response.sdk.single.device.S_UPDATE_DEVICE_BIND_AREA;
import app.client.net.protocol.response.sdk.single.device.S_UPDATE_DEVICE_BIND_SCENE;
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

    public void receiveStateCommand(S_DEVICE_STATE_COMMAND response);

    public void receiveAttrCommand(S_DEVICE_ATTR_COMMAND response);

    public void receiveModeCommand(S_DEVICE_MODE_COMMAND response);

    public void receiveSceneCommand(S_SCENE_COMMAND response);


    public void receiveDeleteDeviceResult(S_DELETE_DEVICE response);

    public void receiveSyncDeviceResult(S_SYNC_DEVICE response);



    public void receiveAddDeviceResult(S_ADD_DEVICE response);

    public void receiveUpdateDeviceResult(S_UPDATE_DEVICE response);

    public void receiveUpdateDeviceBindAreaResult(S_UPDATE_DEVICE_BIND_AREA response);

    public void receiveUpdateDeviceBindSceneResult(S_UPDATE_DEVICE_BIND_SCENE response);


    public void receiveAddDeviceBatchResult(S_ADD_DEVICE_BATCH response);

    public void receiveUpdateDeviceBatchResult(S_UPDATE_DEVICE_BATCH response);

    public void receiveUpdateDeviceBindAreaBatchResult(S_UPDATE_DEVICE_BIND_AREA_BATCH response);

    public void receiveUpdateDeviceBindSceneBatchResult(S_UPDATE_DEVICE_BIND_SCENE_BATCH response);


    public void receiveDockerSpeak(S_DOCKER_SPEAK reponse);

}
