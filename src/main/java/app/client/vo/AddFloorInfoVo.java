package app.client.vo;

/**
 * Created by zh on 2018/2/28.
 */
public class AddFloorInfoVo {

    private String floorId;

    private String floorName;

    private String bindHomeId;

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getBindHomeId() {
        return bindHomeId;
    }

    public void setBindHomeId(String bindHomeId) {
        this.bindHomeId = bindHomeId;
    }
}
