package app.client.testchain.sdk.protocol.floor;

import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.sdk.floor.C_ADD_FLOOR_C;
import app.client.net.protocol.request.sdk.vo.AddFloorInfoVo;
import app.client.testchain.ProtocolListenNode;
import app.client.testchain.sdk.SdkTestConst;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zh on 2018/2/27.
 */
public class AddFloorCommandNode extends ProtocolListenNode {
    @Override
    public void doExecute() {
        List<AddFloorInfoVo> addFloorInfoVoList = new ArrayList<>();

        String floorTid1 = SdkTestConst.FIRST_FLOOR_TID;
        String floorName1 = SdkTestConst.FIRST_FLOOR_TID;
        String bindHomeId1 = SdkTestConst.FIRST_HOME_TID;

        AddFloorInfoVo addFloorInfoVo1 = new AddFloorInfoVo();
        addFloorInfoVo1.setFloorId(floorTid1);
        addFloorInfoVo1.setFloorName(floorName1);
        addFloorInfoVo1.setBindHomeId(bindHomeId1);

        String floorTid2 = SdkTestConst.SECOND_FLOOR_TID;
        String floorName2 = SdkTestConst.SECOND_FLOOR_NAME;
        String bindHomeId2 = SdkTestConst.SECOND_HOME_TID;

        AddFloorInfoVo addFloorInfoVo2 = new AddFloorInfoVo();
        addFloorInfoVo2.setFloorId(floorTid2);
        addFloorInfoVo2.setFloorName(floorName2);
        addFloorInfoVo2.setBindHomeId(bindHomeId2);

        addFloorInfoVoList.add(addFloorInfoVo1);
        addFloorInfoVoList.add(addFloorInfoVo2);

        C_ADD_FLOOR_C protocol = ProtocolFactory.createRequestProtocol(C_ADD_FLOOR_C.class,
                userSession.getCtx());
        protocol.setAddFloorInfoVoList(addFloorInfoVoList);
        userSession.sendMsg(protocol);
    }

    @Override
    public boolean canExecuteImmediately(){
        return true;
    }
}
