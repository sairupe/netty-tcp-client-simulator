package app.client.testchain.sdk.protocol.area;

import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.sdk.batch.area.C_ADD_AREA_BATCH;
import app.client.testchain.ProtocolListenNode;
import app.client.testchain.sdk.SdkTestConst;
import app.client.vo.AddAreaInfoVo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zh on 2018/2/27.
 */
public class AddAreaBatchDynamicTidCommandNode extends ProtocolListenNode {

    private String robotMac;

    @Override
    public void doExecute() {
        List<AddAreaInfoVo> addAreaInfoVoList = new ArrayList<>();

        String areaTid1 = SdkTestConst.FIRST_AREA_TID + robotMac;
        String areaName1 = SdkTestConst.FIRST_AREA_NAME;
        String bindFloorId1 = SdkTestConst.EMPTY_STRING;
        AddAreaInfoVo addAreaInfoVo1 = new AddAreaInfoVo();
        addAreaInfoVo1.setAreaId(areaTid1);
        addAreaInfoVo1.setAreaName(areaName1);
        addAreaInfoVo1.setBindFloorId(bindFloorId1);

        String areaTid2 = SdkTestConst.SECOND_AREA_TID + robotMac;
        String areaName2 = SdkTestConst.SECOND_AREA_NAME;
        String bindFloorId2 = SdkTestConst.EMPTY_STRING;
        AddAreaInfoVo addAreaInfoVo2 = new AddAreaInfoVo();
        addAreaInfoVo2.setAreaId(areaTid2);
        addAreaInfoVo2.setAreaName(areaName2);
        addAreaInfoVo2.setBindFloorId(bindFloorId2);

        addAreaInfoVoList.add(addAreaInfoVo1);
        addAreaInfoVoList.add(addAreaInfoVo2);

        C_ADD_AREA_BATCH protocol = ProtocolFactory.createRequestProtocol(C_ADD_AREA_BATCH.class,
                userSession.getCtx());
        protocol.setAddAreaInfoVoList(addAreaInfoVoList);
        userSession.sendMsg(protocol);
    }

    public String getRobotMac() {
        return robotMac;
    }

    public void setRobotMac(String robotMac) {
        this.robotMac = robotMac;
    }

    @Override
    public boolean canExecuteImmediately(){
        return true;
    }
}
