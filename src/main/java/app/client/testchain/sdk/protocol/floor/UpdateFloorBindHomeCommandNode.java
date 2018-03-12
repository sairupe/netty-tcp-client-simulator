package app.client.testchain.sdk.protocol.floor;

import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.sdk.area.C_UPDATE_AREA_BIND_FLOOR;
import app.client.net.protocol.request.sdk.floor.C_UPDATE_FLOOR_BIND_HOME;
import app.client.net.protocol.request.sdk.floor.C_UPDATE_FLOOR_C;
import app.client.net.protocol.request.sdk.vo.AddFloorInfoVo;
import app.client.net.protocol.request.sdk.vo.UpdateAreaBindFloor;
import app.client.net.protocol.request.sdk.vo.UpdateFloorBindHome;
import app.client.testchain.ProtocolListenNode;
import app.client.testchain.sdk.SdkTestConst;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zh on 2018/2/27.
 */
public class UpdateFloorBindHomeCommandNode extends ProtocolListenNode {
    @Override
    public void doExecute() {

        String floorId1 = SdkTestConst.FIRST_FLOOR_TID;
        String homeId1 = SdkTestConst.FIRST_HOME_TID;

        UpdateFloorBindHome updateFloorBindHome1 = new UpdateFloorBindHome();
        updateFloorBindHome1.setFloorId(floorId1);
        updateFloorBindHome1.setHomeId(homeId1);

        String floorId2 = SdkTestConst.SECOND_FLOOR_TID;
        String homeId2 = SdkTestConst.SECOND_HOME_TID;

        UpdateFloorBindHome updateFloorBindHome2 = new UpdateFloorBindHome();
        updateFloorBindHome2.setFloorId(floorId2);
        updateFloorBindHome2.setHomeId(homeId2);

        List<UpdateFloorBindHome> updateList = new ArrayList<>();
        updateList.add(updateFloorBindHome1);
        updateList.add(updateFloorBindHome2);

        C_UPDATE_FLOOR_BIND_HOME updateFloorBindHome = ProtocolFactory.createRequestProtocol(C_UPDATE_FLOOR_BIND_HOME.class,
                userSession.getCtx());
        updateFloorBindHome.setUpdateFloorBindHomeList(updateList);
        userSession.sendMsg(updateFloorBindHome);
    }

    @Override
    public boolean canExecuteImmediately(){
        return true;
    }
}
