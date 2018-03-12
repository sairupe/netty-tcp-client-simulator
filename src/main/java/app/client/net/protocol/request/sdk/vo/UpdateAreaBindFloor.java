package app.client.net.protocol.request.sdk.vo;

/**
 * Created by zh on 2018/3/10.
 */
public class UpdateAreaBindFloor {

    private String areaId;

    private String floorId;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }
}
