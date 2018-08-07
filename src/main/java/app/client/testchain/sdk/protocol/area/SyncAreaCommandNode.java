package app.client.testchain.sdk.protocol.area;

import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.sdk.batch.area.C_SYNC_AREA;
import app.client.net.protocol.request.sdk.vo.AddAreaInfoVo;
import app.client.testchain.ProtocolListenNode;
import app.client.testchain.sdk.SdkTestConst;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zh on 2018/2/27.
 */
public class SyncAreaCommandNode extends ProtocolListenNode {
    @Override
    public void doExecute() {

        List<AddAreaInfoVo> addAreaInfoVoList = new ArrayList<>();

        String areaTid1 = SdkTestConst.FIRST_AREA_TID;
        String areaName1 = SdkTestConst.FIRST_AREA_NAME;
        String floorTid1 = SdkTestConst.FIRST_FLOOR_TID;
        AddAreaInfoVo addAreaInfoVo1 = new AddAreaInfoVo();
        addAreaInfoVo1.setAreaId(areaTid1);
        addAreaInfoVo1.setAreaName(areaName1);
        addAreaInfoVo1.setBindFloorId(floorTid1);

        String areaTid2 = SdkTestConst.SECOND_AREA_TID;
        String areaName2 = SdkTestConst.SECOND_AREA_NAME;
        String floorTid2 = SdkTestConst.SECOND_FLOOR_TID;
        AddAreaInfoVo addAreaInfoVo2 = new AddAreaInfoVo();
        addAreaInfoVo2.setAreaId(areaTid2);
        addAreaInfoVo2.setAreaName(areaName2);
        addAreaInfoVo2.setBindFloorId(floorTid2);

        addAreaInfoVoList.add(addAreaInfoVo1);
        addAreaInfoVoList.add(addAreaInfoVo2);

        C_SYNC_AREA protocol = ProtocolFactory.createRequestProtocol(C_SYNC_AREA.class,
                userSession.getCtx());
        protocol.setSyncAreaInfoVoList(addAreaInfoVoList);
        userSession.sendMsg(protocol);
    }

    @Override
    public boolean canExecuteImmediately(){
        return true;
    }
}
