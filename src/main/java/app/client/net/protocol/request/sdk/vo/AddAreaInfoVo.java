package app.client.net.protocol.request.sdk.vo;

/**
 * Created by zh on 2018/2/28.
 */
public class AddAreaInfoVo {

    private String areaId;

    private String areaName;

    private String bindFloorId;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getBindFloorId() {
        return bindFloorId;
    }

    public void setBindFloorId(String bindFloorId) {
        this.bindFloorId = bindFloorId;
    }
}
