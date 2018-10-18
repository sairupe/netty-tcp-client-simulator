package app.client.testchain.sdk.protocol.home;

import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.sdk.batch.home.C_DELETE_HOME;
import app.client.testchain.ProtocolListenNode;
import app.client.testchain.sdk.SdkTestConst;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zh on 2018/2/27.
 */
public class DeleteHomeCommandNode extends ProtocolListenNode {
    @Override
    public void doExecute() {
        String firstHomeId = SdkTestConst.FIRST_HOME_TID;
        String secondHomeId = SdkTestConst.SECOND_HOME_TID;
        List<String> deleteHomeIdList = new ArrayList<>();
        deleteHomeIdList.add(firstHomeId);
        deleteHomeIdList.add(secondHomeId);

        C_DELETE_HOME protocol = ProtocolFactory.createRequestProtocol(C_DELETE_HOME.class,
                userSession.getCtx());
        protocol.setDeleteHomeIdList(deleteHomeIdList);
        userSession.sendMsg(protocol);
    }

    @Override
    public boolean canExecuteImmediately(){
        return true;
    }
}
