package app.client.testchain.sdk.protocol.floor;

import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.sdk.batch.floor.C_DELETE_FLOOR;
import app.client.testchain.ProtocolListenNode;
import app.client.testchain.sdk.SdkTestConst;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zh on 2018/2/27.
 */
public class DeleteFloorCommandNode extends ProtocolListenNode {
    @Override
    public void doExecute() {
        String firstFloorId = SdkTestConst.FIRST_FLOOR_TID;
        String secondFloorId = SdkTestConst.SECOND_FLOOR_TID;
        List<String> deleteFloorIdList = new ArrayList<>();
        deleteFloorIdList.add(firstFloorId);
        deleteFloorIdList.add(secondFloorId);

        C_DELETE_FLOOR protocol = ProtocolFactory.createRequestProtocol(C_DELETE_FLOOR.class,
                userSession.getCtx());
        protocol.setDeleteFloorIdList(deleteFloorIdList);
        userSession.sendMsg(protocol);
    }

    @Override
    public boolean canExecuteImmediately(){
        return true;
    }
}
