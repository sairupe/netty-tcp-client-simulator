package app.client.testchain.sdk.protocol.home;

import app.client.net.protocol.ProtocolFactory;
import app.client.net.protocol.request.sdk.batch.home.C_ADD_HOME_BATCH;
import app.client.testchain.ProtocolListenNode;
import app.client.testchain.sdk.SdkTestConst;
import com.gowild.sdk.vo.db.AddHomeInfoVo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zh on 2018/2/27.
 */
public class AddHomeBatchCommandNode extends ProtocolListenNode {
    @Override
    public void doExecute() {
        List<AddHomeInfoVo> addHomeInfoVoList = new ArrayList<>();

        String homeTid1 = SdkTestConst.FIRST_HOME_TID;
        String homeName1 = SdkTestConst.FIRST_HOME_NAME;
        AddHomeInfoVo addHomeInfoVo1 = new AddHomeInfoVo();
        addHomeInfoVo1.setHomeTid(homeTid1);
        addHomeInfoVo1.setHomeName(homeName1);

        String homeTid2 = SdkTestConst.SECOND_HOME_TID;
        String homeName2 = SdkTestConst.SECOND_HOME_NAME;
        AddHomeInfoVo addHomeInfoVo2 = new AddHomeInfoVo();
        addHomeInfoVo2.setHomeTid(homeTid2);
        addHomeInfoVo2.setHomeName(homeName2);

        addHomeInfoVoList.add(addHomeInfoVo1);
        addHomeInfoVoList.add(addHomeInfoVo2);

        C_ADD_HOME_BATCH protocol = ProtocolFactory.createRequestProtocol(C_ADD_HOME_BATCH.class,
                userSession.getCtx());
        protocol.setAddHomeInfoVoList(addHomeInfoVoList);
        userSession.sendMsg(protocol);
    }

    @Override
    public boolean canExecuteImmediately(){
        return true;
    }
}
