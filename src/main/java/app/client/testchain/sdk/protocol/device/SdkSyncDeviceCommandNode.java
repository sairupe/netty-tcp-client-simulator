package app.client.testchain.sdk.protocol.device;

import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.sdk.device.C_SYNC_DEVICE_C;
import app.client.net.protocol.request.sdk.vo.AddDeviceInfoVo;
import app.client.testchain.ProtocolListenNode;
import app.client.testchain.sdk.SdkTestConst;
import com.gowild.sdk.basic.constant.SdkConstant;
import com.gowild.sdk.basic.enums.DeviceTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zh on 2017/11/21.
 */
public class SdkSyncDeviceCommandNode extends ProtocolListenNode {
    @Override
    public void doExecute() {

        String device1 = SdkTestConst.FIRST_DEVICE_UID;
        String device1Name = SdkTestConst.FIRST_DEVICE_NAME;
        String device1SN = SdkTestConst.FIRST_DEVICE_SN;
        String device1AreaId = SdkTestConst.FIRST_AREA_TID;
        String device1SceneId = "";
        AddDeviceInfoVo addDeviceInfoVo1 = new AddDeviceInfoVo();
        addDeviceInfoVo1.setDeviceId(device1);
        addDeviceInfoVo1.setDeviceName(device1Name);
        addDeviceInfoVo1.setOpenStatus(SdkConstant.DEFAULT_OPEN_STATUS_INTVALUE);
        addDeviceInfoVo1.setOnlineStatus(SdkConstant.DEFAULT_ONLINE_STATUS_INTVALUE);
        addDeviceInfoVo1.setDeviceSn(device1SN);
//        addDeviceInfoVo1.setAreaId(device1AreaId);
//        addDeviceInfoVo1.setSceneId(device1SceneId);
        addDeviceInfoVo1.setDeviceType(DeviceTypeEnum.LIGHT.getDeviceType());

        String device2Id = SdkTestConst.SECOND_DEVICE_UID;
        String device2Name = SdkTestConst.SECOND_DEVICE_NAME;
        String device2SN = SdkTestConst.SECOND_DEVICE_SN;
        String device2AreaId = "";
        String device2SceneId = "";
        AddDeviceInfoVo addDeviceInfoVo2 = new AddDeviceInfoVo();
        addDeviceInfoVo2.setDeviceId(device2Id);
        addDeviceInfoVo2.setDeviceName(device2Name);
        addDeviceInfoVo2.setOpenStatus(SdkConstant.DEFAULT_OPEN_STATUS_INTVALUE);
        addDeviceInfoVo2.setOnlineStatus(SdkConstant.DEFAULT_ONLINE_STATUS_INTVALUE);
        addDeviceInfoVo2.setDeviceSn(device2SN);
//        addDeviceInfoVo2.setAreaId(device2AreaId);
//        addDeviceInfoVo2.setSceneId(device2SceneId);
        addDeviceInfoVo2.setDeviceType(DeviceTypeEnum.LIGHT.getDeviceType());

        List<AddDeviceInfoVo> addDeviceInfoVoList = new ArrayList<>();
        addDeviceInfoVoList.add(addDeviceInfoVo1);
        addDeviceInfoVoList.add(addDeviceInfoVo2);

        C_SYNC_DEVICE_C addDevices = ProtocolFactory.createRequestProtocol(C_SYNC_DEVICE_C.class,
                userSession.getCtx());
        addDevices.setAddDeviceInfoVoList(addDeviceInfoVoList);
        userSession.sendMsg(addDevices);
    }

    @Override
    public boolean canExecuteImmediately(){
        return true;
    }
}
