package app.client.testchain.sdk.protocol;

import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.sdk.device.C_DELETE_DEVICE_C;
import app.client.testchain.ProtocolListenNode;
import app.client.testchain.sdk.SdkTestConst;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zh on 2017/11/21.
 */
public class SdkDeleteDeviceCommandNode extends ProtocolListenNode {
    @Override
    public void doExecute() {

        String deleteId1 = SdkTestConst.FIRST_DEVICE_UID;
        String deleteId2 = SdkTestConst.SECOND_DEVICE_UID;
        List<String> deleteList = new ArrayList<>();
        deleteList.add(deleteId1);
        deleteList.add(deleteId2);

        C_DELETE_DEVICE_C deleteDevices = ProtocolFactory.createRequestProtocol(C_DELETE_DEVICE_C.class,
                userSession.getCtx());
        deleteDevices.setDeleteDeviceIdList(deleteList);
        userSession.sendMsg(deleteDevices);
    }

    @Override
    public boolean canExecuteImmediately(){
        return true;
    }
}
