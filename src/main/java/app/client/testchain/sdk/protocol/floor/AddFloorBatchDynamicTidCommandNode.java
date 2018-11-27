package app.client.testchain.sdk.protocol.floor;

import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.sdk.batch.floor.C_ADD_FLOOR_BATCH;
import app.client.testchain.ProtocolListenNode;
import app.client.testchain.sdk.SdkTestConst;
import app.client.vo.AddFloorInfoVo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zh on 2018/2/27.
 */
public class AddFloorBatchDynamicTidCommandNode extends ProtocolListenNode {

    private String rotbotMac;

    @Override
    public void doExecute() {
        List<AddFloorInfoVo> addFloorInfoVoList = new ArrayList<>();

        String floorTid1 = SdkTestConst.FIRST_FLOOR_TID + rotbotMac;
        String floorName1 = SdkTestConst.FIRST_FLOOR_NAME;
        String bindHomeId1 = SdkTestConst.EMPTY_STRING;

        AddFloorInfoVo addFloorInfoVo1 = new AddFloorInfoVo();
        addFloorInfoVo1.setFloorId(floorTid1);
        addFloorInfoVo1.setFloorName(floorName1);
        addFloorInfoVo1.setBindHomeId(bindHomeId1);

        String floorTid2 = SdkTestConst.SECOND_FLOOR_TID + rotbotMac;
        String floorName2 = SdkTestConst.SECOND_FLOOR_NAME;
        String bindHomeId2 = SdkTestConst.EMPTY_STRING;

        AddFloorInfoVo addFloorInfoVo2 = new AddFloorInfoVo();
        addFloorInfoVo2.setFloorId(floorTid2);
        addFloorInfoVo2.setFloorName(floorName2);
        addFloorInfoVo2.setBindHomeId(bindHomeId2);

        addFloorInfoVoList.add(addFloorInfoVo1);
        addFloorInfoVoList.add(addFloorInfoVo2);

        C_ADD_FLOOR_BATCH protocol = ProtocolFactory.createRequestProtocol(C_ADD_FLOOR_BATCH.class,
                userSession.getCtx());
        protocol.setAddFloorInfoVoList(addFloorInfoVoList);
        userSession.sendMsg(protocol);
    }

    public String getRotbotMac() {
        return rotbotMac;
    }

    public void setRotbotMac(String rotbotMac) {
        this.rotbotMac = rotbotMac;
    }

    @Override
    public boolean canExecuteImmediately(){
        return true;
    }
}
