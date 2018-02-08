package app.client.testchain.sdk.protocol;

import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.sdk.C_BIND_DEVICE_C;
import app.client.net.protocol.request.sdk.C_DEVICE_LOGIN;
import app.client.net.protocol.request.sdk.vo.AddDeviceInfoVo;
import app.client.testchain.ProtocolListenNode;
import com.gowild.basic.constant.SdkConstant;
import com.gowild.basic.enums.DeviceTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zh on 2017/11/21.
 */
public class SdkAddDeviceCommandNode extends ProtocolListenNode {
    @Override
    public void doExecute() {

        String device1 = "12315-1";
        String device1AreaId = "6";
        String device1SceneId = "6";
        AddDeviceInfoVo addDeviceInfoVo1 = new AddDeviceInfoVo();
        addDeviceInfoVo1.setDeviceId(device1);
        addDeviceInfoVo1.setDeviceName(device1);
        addDeviceInfoVo1.setOpenStatus(SdkConstant.DEFAULT_OPEN_STATUS_INTVALUE);
        addDeviceInfoVo1.setOnlineStatus(SdkConstant.DEFAULT_ONLINE_STATUS_INTVALUE);
        addDeviceInfoVo1.setDeviceSn(device1);
        addDeviceInfoVo1.setAreaId(device1AreaId);
        addDeviceInfoVo1.setSceneId(device1SceneId);
        addDeviceInfoVo1.setDeviceType(DeviceTypeEnum.LIGHT.getDeviceType());

        String device2 = "12315-2";
        String device2AreaId = "6";
        String device2SceneId = "6";
        AddDeviceInfoVo addDeviceInfoVo2 = new AddDeviceInfoVo();
        addDeviceInfoVo2.setDeviceId(device2);
        addDeviceInfoVo2.setDeviceName(device2);
        addDeviceInfoVo2.setOpenStatus(SdkConstant.DEFAULT_OPEN_STATUS_INTVALUE);
        addDeviceInfoVo2.setOnlineStatus(SdkConstant.DEFAULT_ONLINE_STATUS_INTVALUE);
        addDeviceInfoVo2.setDeviceSn(device2);
        addDeviceInfoVo2.setAreaId(device2AreaId);
        addDeviceInfoVo2.setSceneId(device2SceneId);
        addDeviceInfoVo2.setDeviceType(DeviceTypeEnum.LIGHT.getDeviceType());

        List<AddDeviceInfoVo> addDeviceInfoVoList = new ArrayList<>();
        addDeviceInfoVoList.add(addDeviceInfoVo1);
        addDeviceInfoVoList.add(addDeviceInfoVo2);

        C_BIND_DEVICE_C addDevices = ProtocolFactory.createRequestProtocol(C_BIND_DEVICE_C.class,
                userSession.getCtx());
        addDevices.setAddDeviceInfoVoList(addDeviceInfoVoList);
        userSession.sendMsg(addDevices);
    }

    @Override
    public boolean canExecuteImmediately(){
        return true;
    }
}
