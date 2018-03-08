package app.client.testchain.sdk.protocol.device;

import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.sdk.device.C_GET_ALL_XB_BIND_MASTER;
import app.client.net.protocol.request.sdk.device.C_UPDATE_DEVICE_C;
import app.client.net.protocol.request.sdk.vo.AddDeviceInfoVo;
import app.client.testchain.ProtocolListenNode;
import app.client.testchain.sdk.SdkTestConst;
import com.gowild.basic.constant.SdkConstant;
import com.gowild.basic.enums.DeviceTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zh on 2017/11/21.
 */
public class SdkGetXbBindAllMasterCommandNode extends ProtocolListenNode {
    @Override
    public void doExecute() {

        String xbMac = SdkTestConst.XB_MAC;

        C_GET_ALL_XB_BIND_MASTER getAllXbBindMaster = ProtocolFactory.createRequestProtocol(C_GET_ALL_XB_BIND_MASTER.class,
                userSession.getCtx());
        getAllXbBindMaster.setXbMac(xbMac);
        userSession.sendMsg(getAllXbBindMaster);
    }

    @Override
    public boolean canExecuteImmediately(){
        return true;
    }
}
