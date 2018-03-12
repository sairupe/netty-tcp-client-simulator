package app.client.net.protocol.request.sdk.vo;

/**
 * Created by zh on 2018/3/10.
 */
public class UpdateFloorBindHome {

    private String floorId;

    private String homeId;

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getHomeId() {
        return homeId;
    }

    public void setHomeId(String homeId) {
        this.homeId = homeId;
    }
}
