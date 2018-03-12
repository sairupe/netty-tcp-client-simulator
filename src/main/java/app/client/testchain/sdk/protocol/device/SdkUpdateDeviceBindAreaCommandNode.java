package app.client.testchain.sdk.protocol.device;

import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.sdk.device.C_UPDATE_DEVICE_BIND_AREA;
import app.client.net.protocol.request.sdk.vo.UpdateDeviceBindArea;
import app.client.testchain.ProtocolListenNode;
import app.client.testchain.sdk.SdkTestConst;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zh on 2017/11/21.
 */
public class SdkUpdateDeviceBindAreaCommandNode extends ProtocolListenNode {
    @Override
    public void doExecute() {

        String device1 = SdkTestConst.FIRST_DEVICE_UID;
        String areaId1 = SdkTestConst.FIRST_AREA_TID;

        UpdateDeviceBindArea updateDeviceBindArea1 = new UpdateDeviceBindArea();
        updateDeviceBindArea1.setDeviceId(device1);
        updateDeviceBindArea1.setAreaId(areaId1);

        String device2 = SdkTestConst.SECOND_DEVICE_UID;
        String areaId2 = SdkTestConst.FIRST_AREA_TID;

        UpdateDeviceBindArea updateDeviceBindArea2 = new UpdateDeviceBindArea();
        updateDeviceBindArea2.setDeviceId(device2);
        updateDeviceBindArea2.setAreaId(areaId2);

        List<UpdateDeviceBindArea> updateList = new ArrayList<>();
        updateList.add(updateDeviceBindArea1);
        updateList.add(updateDeviceBindArea2);

        C_UPDATE_DEVICE_BIND_AREA updateDeviceBindArea = ProtocolFactory.createRequestProtocol(C_UPDATE_DEVICE_BIND_AREA.class,
                userSession.getCtx());
        updateDeviceBindArea.setUpdateDeviceBindAreaList(updateList);
        userSession.sendMsg(updateDeviceBindArea);
    }

    @Override
    public boolean canExecuteImmediately(){
        return true;
    }
}
