package app.client.testchain.sdk.protocol.area;

import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.sdk.batch.area.C_UPDATE_AREA_BATCH;
import app.client.net.protocol.request.sdk.vo.AddAreaInfoVo;
import app.client.testchain.ProtocolListenNode;
import app.client.testchain.sdk.SdkTestConst;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zh on 2018/2/27.
 */
public class UpdateAreaCommandNode extends ProtocolListenNode {
    @Override
    public void doExecute() {

        List<AddAreaInfoVo> updateAreaInfoVoList = new ArrayList<>();

        String firstAreaTid = SdkTestConst.FIRST_AREA_TID;
        String secondAreaTid = SdkTestConst.SECOND_AREA_TID;
        String firstAreaName = SdkTestConst.FIRST_AREA_NAME;
        String secondAreaName = SdkTestConst.SECOND_AREA_NAME;

        AddAreaInfoVo addAreaInfoVo1 = new AddAreaInfoVo();
        addAreaInfoVo1.setAreaId(firstAreaTid);
        addAreaInfoVo1.setAreaName(secondAreaTid);

        AddAreaInfoVo addAreaInfoVo2 = new AddAreaInfoVo();
        addAreaInfoVo2.setAreaId(secondAreaTid);
        addAreaInfoVo2.setAreaName(secondAreaName);

        updateAreaInfoVoList.add(addAreaInfoVo1);
        updateAreaInfoVoList.add(addAreaInfoVo2);


        C_UPDATE_AREA_BATCH protocol = ProtocolFactory.createRequestProtocol(C_UPDATE_AREA_BATCH.class,
                userSession.getCtx());
        protocol.setUpdateAreaInfoVoList(updateAreaInfoVoList);
        userSession.sendMsg(protocol);
    }

    @Override
    public boolean canExecuteImmediately(){
        return true;
    }
}
