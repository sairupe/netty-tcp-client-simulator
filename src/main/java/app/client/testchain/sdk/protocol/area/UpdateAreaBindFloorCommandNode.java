package app.client.testchain.sdk.protocol.area;

import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.sdk.area.C_UPDATE_AREA_BIND_FLOOR;
import app.client.net.protocol.request.sdk.area.C_UPDATE_AREA_C;
import app.client.net.protocol.request.sdk.device.C_UPDATE_DEVICE_BIND_SCENE;
import app.client.net.protocol.request.sdk.vo.AddAreaInfoVo;
import app.client.net.protocol.request.sdk.vo.UpdateAreaBindFloor;
import app.client.net.protocol.request.sdk.vo.UpdateDeviceBindScene;
import app.client.testchain.ProtocolListenNode;
import app.client.testchain.sdk.SdkTestConst;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zh on 2018/2/27.
 */
public class UpdateAreaBindFloorCommandNode extends ProtocolListenNode {
    @Override
    public void doExecute() {

        String areaId1 = SdkTestConst.FIRST_AREA_TID;
        String floorId1 = SdkTestConst.FIRST_FLOOR_TID;

        UpdateAreaBindFloor updateAreaBindFloor1 = new UpdateAreaBindFloor();
        updateAreaBindFloor1.setAreaId(areaId1);
        updateAreaBindFloor1.setFloorId(floorId1);

        String areaId2 = SdkTestConst.SECOND_AREA_TID;
        String floorId2 = SdkTestConst.SECOND_FLOOR_TID;

        UpdateAreaBindFloor updateAreaBindFloor2 = new UpdateAreaBindFloor();
        updateAreaBindFloor2.setAreaId(areaId2);
        updateAreaBindFloor2.setFloorId(floorId2);

        List<UpdateAreaBindFloor> updateList = new ArrayList<>();
        updateList.add(updateAreaBindFloor1);
        updateList.add(updateAreaBindFloor2);

        C_UPDATE_AREA_BIND_FLOOR updateAreaBindFloor = ProtocolFactory.createRequestProtocol(C_UPDATE_AREA_BIND_FLOOR.class,
                userSession.getCtx());
        updateAreaBindFloor.setUpdateAreaBindFloorList(updateList);
        userSession.sendMsg(updateAreaBindFloor);
    }

    @Override
    public boolean canExecuteImmediately(){
        return true;
    }
}
