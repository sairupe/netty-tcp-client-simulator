package app.client.net.protocol.request.sdk.vo;

/**
 * Created by zh on 2018/3/10.
 */
public class UpdateDeviceBindScene {

    private String deviceId;

    private String sceneId;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }
}
